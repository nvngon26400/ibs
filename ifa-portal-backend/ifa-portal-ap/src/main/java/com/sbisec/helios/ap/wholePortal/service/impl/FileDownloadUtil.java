package com.sbisec.helios.ap.wholePortal.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.lowagie.text.Document;
import com.lowagie.text.Header;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.exceptions.BadPasswordException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.SimpleBookmark;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.wholePortal.diversion.enums.VerticalHorizontal;
import com.sbisec.helios.ap.wholePortal.psite.model.GetDealerNameAndRegistNoModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTbMedDisclaimerModel;
import com.sbisec.helios.ap.wholePortal.psite.model.GetTempFileDirForDocumentCategoryIdModel;
import com.sbisec.helios.ap.wholePortal.psite.service.PSiteService;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import eu.medsea.mimeutil.MimeUtil;

/**
 * FileDownloadUtil
 *
 * @author Yoshitaka.Nishida
 *
 */
@Component
public class FileDownloadUtil {
    
    /** PSiteService */
    @Autowired
    private PSiteService pSiteService;
    
    /** MCodeService */
    @Autowired
    private MCodeService mCodeService;
    
    /** Compiled Disclaimer file for Money Plaza A3 vertical */
    private static final String DISCLAIMER_MP_A3_VERTICAL = "DisclaimerMPA3V.jasper";
    
    /** Compiled Disclaimer file for Money Plaza B4 vertical */
    private static final String DISCLAIMER_MP_B4_VERTICAL = "DisclaimerMPB4V.jasper";
    
    /** Compiled Disclaimer file for Money Plaza A4 vertical */
    private static final String DISCLAIMER_MP_A4_VERTICAL = "DisclaimerMPA4V.jasper";
    
    /** Compiled Disclaimer file for Money Plaza B5 vertical */
    private static final String DISCLAIMER_MP_B5_VERTICAL = "DisclaimerMPB5V.jasper";
    
    /** Compiled Disclaimer file for Money Plaza A5 vertical */
    private static final String DISCLAIMER_MP_A5_VERTICAL = "DisclaimerMPA5V.jasper";
    
    /** Compiled Disclaimer file for Money Plaza A3 horizontal */
    private static final String DISCLAIMER_MP_A3_HORIZONTAL = "DisclaimerMPA3H.jasper";
    
    /** Compiled Disclaimer file for Money Plaza B4 horizontal */
    private static final String DISCLAIMER_MP_B4_HORIZONTAL = "DisclaimerMPB4H.jasper";
    
    /** Compiled Disclaimer file for Money Plaza A4 horizontal */
    private static final String DISCLAIMER_MP_A4_HORIZONTAL = "DisclaimerMPA4H.jasper";
    
    /** Compiled Disclaimer file for Money Plaza A5 horizontal */
    private static final String DISCLAIMER_MP_B5_HORIZONTAL = "DisclaimerMPB5H.jasper";
    
    /** Compiled Disclaimer file for Money Plaza B5 horizontal */
    private static final String DISCLAIMER_MP_A5_HORIZONTAL = "DisclaimerMPA5H.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza A3 vertical */
    private static final String DISCLAIMER_A3_VERTICAL = "DisclaimerA3V.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza B4 vertical */
    private static final String DISCLAIMER_B4_VERTICAL = "DisclaimerB4V.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza A4 vertical */
    private static final String DISCLAIMER_A4_VERTICAL = "DisclaimerA4V.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza B5 vertical */
    private static final String DISCLAIMER_B5_VERTICAL = "DisclaimerB5V.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza A5 vertical */
    private static final String DISCLAIMER_A5_VERTICAL = "DisclaimerA5V.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza A3 horizontal */
    private static final String DISCLAIMER_A3_HORIZONTAL = "DisclaimerA3H.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza B3 horizontal */
    private static final String DISCLAIMER_B4_HORIZONTAL = "DisclaimerB4H.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza A4 horizontal */
    private static final String DISCLAIMER_A4_HORIZONTAL = "DisclaimerA4H.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza B5 horizontal */
    private static final String DISCLAIMER_B5_HORIZONTAL = "DisclaimerB5H.jasper";
    
    /** Compiled Disclaimer file for non Money Plaza A5 horizontal */
    private static final String DISCLAIMER_A5_HORIZONTAL = "DisclaimerA5H.jasper";
    
    /** TB_MED_DISCLAIMER DISCLAIMER_ID */
    private static final String DISCLAIMER_ID_MP = "01";
    
    private static final String DISCLAIMER_ID_ETC = "99";
    
    /** TB_MED_DISCLAIMER ITEM_MAME */
    private static final String IMPORTANT_POINTS = "importantPoints";
    
    private static final String ABOUT_FINANCIAL_PRODUCT_BROKERAGE = "aboutFinancialProductBrokerage";
    
    private static final String ABOUT_BANK_AGENT = "aboutBankAgent";
    
    private static final String ABOUT_INSURANCE_AGENCY = "aboutInsuranceAgency";
    
    private static final String BROKER = "broker";
    
    private static final String TYPE2_BROKER = "type2Broker";
    
    private static final String BANK_AGENT = "bankAgent";
    
    private static final String MONEYLENDER = "moneylender";
    
    private static final String MSG1 = "msg1";
    
    private static final String MSG2 = "msg2";
    
    private static final String INSTRUMENTS_FIRMS = "instrumentsFirms";
    
    private static final String REGISTRATION_NUMBER = "registrationNumber";
    
    private static final String MEMBER_ASSOCIATIONS = "memberAssociations";
    
    private static final String MIME_TYPE_PDF = "application/pdf";
    
    private static final String MP_BROKER_CODE = "0509";
    
    private static final String JASPER_PATH = "classpath:jasper/";
    
    private static final String MPLOGO = "MPlogo";
    
    private static final String MPLOGO_FILE = "MPlogo.gif";
    
    private static final String MIME_DETECTOR = "eu.medsea.mimeutil.detector.MagicMimeMimeDetector";
    
    private static final String APPLICATION_PROPERTIES = "application.properties";
    
    private static final String UTF_8 = "UTF-8";
    
    private static final String CREATION_DATE = "CreationDate";
    
    private static final String MOD_DATE = "ModDate";
    
    /** Limiting the size of the original data to be appended disclaimer for resources/application.properties */
    private static final String A5_MM_SIZE_TO = "A5_SIZE_TO";
    
    private static final String B5_MM_SIZE_TO = "B5_SIZE_TO";
    
    private static final String A4_MM_SIZE_TO = "A4_SIZE_TO";
    
    private static final String B4_MM_SIZE_TO = "B4_SIZE_TO";
    
    private static final int A5_MM_SIZE_DEFAULT = 233;
    
    private static final int B5_MM_SIZE_DEFAULT = 277;
    
    private static final int A4_MM_SIZE_DEFAULT = 330;
    
    private static final int B4_MM_SIZE_DEFAULT = 392;
    
    private static final float A5_SCALE_SIZE = 0.70f;
    
    private static final float B5_SCALE_SIZE = 0.86f;
    
    private static final float A4_SCALE_SIZE = 1.00f;
    
    private static final float B4_SCALE_SIZE = 1.22f;
    
    private static final float A3_SCALE_SIZE = 1.41f;
    
    private static final String DISCLAIMER_FUNC_ID = "M7";
    
    private static final String DISCLAIMER_CAT_ID = "0";
    
    // 対象仲介業者
    private static final String CODE_TYPE = "22";
    
    /** Limit size to hold at point */
    private static float A5_POINT_SIZE_TO;
    
    private static float B5_POINT_SIZE_TO;
    
    private static float A4_POINT_SIZE_TO;
    
    private static float B4_POINT_SIZE_TO;
    /**
     * static Initialization block<br>
     * Register a MimeDetector and add it to the MimeDetector registry.
     * Set paper size from application.properties(mm -> point)
     */
    static {
        MimeUtil.registerMimeDetector(MIME_DETECTOR);
        try {
            // TODO オンコーディングではないが、application.propertiesが持つ値をどうするか？課題化
            PropertiesFactoryBean pfb = new PropertiesFactoryBean();
            pfb.setLocation(new ClassPathResource(APPLICATION_PROPERTIES));
            pfb.setFileEncoding(UTF_8);
            pfb.afterPropertiesSet();
            Properties properties = pfb.getObject();
            A5_POINT_SIZE_TO = chengePoint(Integer.parseInt(properties.getProperty(A5_MM_SIZE_TO)));
            B5_POINT_SIZE_TO = chengePoint(Integer.parseInt(properties.getProperty(B5_MM_SIZE_TO)));
            A4_POINT_SIZE_TO = chengePoint(Integer.parseInt(properties.getProperty(A4_MM_SIZE_TO)));
            B4_POINT_SIZE_TO = chengePoint(Integer.parseInt(properties.getProperty(B4_MM_SIZE_TO)));
        } catch (Exception e) {
            A5_POINT_SIZE_TO = chengePoint(A5_MM_SIZE_DEFAULT);
            B5_POINT_SIZE_TO = chengePoint(B5_MM_SIZE_DEFAULT);
            A4_POINT_SIZE_TO = chengePoint(A4_MM_SIZE_DEFAULT);
            B4_POINT_SIZE_TO = chengePoint(B4_MM_SIZE_DEFAULT);
        }
    }
    
    private static final float MM_INCH = 25.4F;
    
    private static final float PT_INCH = 72F;
    
    protected static final String TEMPORARY_PDF_FILE_NAME = "%s_%s_%s.pdf";
    
    protected static final String PDF_TEMPORARY_DATE_PATTERN = "yyyyMMddHHmmssSSS";
    
    /**
     * chengePoint
     * Change size from millimeter to point
     * @param  int mm
     * @return float point
     */
    private static float chengePoint(int mm) {
        
        return (mm / MM_INCH) * PT_INCH;
    }
    
    @Autowired
    private ResourceLoader resourceLoader;
    
    private Logger logger = LoggerFactory.getLogger(FileDownloadUtil.class);
    
    /**
     * isBroker<br>
     * Broker judgment of login user
     * @return true:Broker / false:not Broker
     */
    private boolean isBroker(DataList<GetDealerNameAndRegistNoModel> dataList) {
        
        if (dataList.getTotalSize() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * addDisclaimer<br>
     * And outputs it to the OutputStream of the response by adding a disclaimer to read the file.
     *
     * @param inputStream
     * @param outputStream
     * @param brokerCode
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addDisclaimer(FileInputStream inputStream, OutputStream outputStream,
            DataList<GetDealerNameAndRegistNoModel> dataList, String brokerCode) throws Exception {
        
        PdfReader inputPdf = null;
        PdfReader toAddPdf = null;
        Document document = null;
        PdfCopy writer = null;
        boolean isVertical = true;
        int numberOfPages = 0;
        float longSize = 0f;
        
        // For non-broker download files directly
        if (!isBroker(dataList)) {
            IOUtils.copy(inputStream, outputStream);
            return;
        }
        
        try {
            inputPdf = new PdfReader(inputStream);
            numberOfPages = inputPdf.getNumberOfPages();
            Rectangle pageSizeWithRotation = inputPdf.getPageSizeWithRotation(numberOfPages);
            
            if (pageSizeWithRotation.getHeight() > pageSizeWithRotation.getWidth()) {
                longSize = pageSizeWithRotation.getHeight();
                isVertical = true;
            } else {
                longSize = pageSizeWithRotation.getWidth();
                isVertical = false;
            }
            
            // Create disclaimer
            toAddPdf = outDisclaimerPdf(longSize, isVertical, brokerCode);
            if (toAddPdf == null) {
                // 対象仲介業者に未登録又は
                // 仲介業者別ディスクレーマPDFが無い時はJapser disclaimerで作成
                // Create Japser disclaimer
                toAddPdf = outJasper(numberOfPages, longSize, isVertical, dataList, brokerCode);
            }
            
            // Build a stream joining disclaimer
            ArrayList master = new ArrayList();
            inputPdf.consolidateNamedDestinations();
            document = new Document(inputPdf.getPageSizeWithRotation(1));
            writer = new PdfCopy(document, outputStream);
            
            // Save bookmarks in ArrayList
            List bookmarks = SimpleBookmark.getBookmark(inputPdf);
            if (bookmarks != null) {
                master.addAll(bookmarks);
            }
            
            // Set PDF Version
            writer.setPdfVersion(inputPdf.getPdfVersion());
            document.open();
            
            // Add PDF Property
            HashMap<String, String> infoMap = inputPdf.getInfo();
            for (Map.Entry<String, String> entry : infoMap.entrySet()) {
                String key = entry.getKey();
                // Other than creation date and update date
                if (!CREATION_DATE.equals(key) && !MOD_DATE.equals(key)) {
                    document.add(new Header(key, entry.getValue()));
                }
            }
            
            // Add download file
            for (int pageNumber = 1; pageNumber <= numberOfPages; pageNumber++) {
                writer.addPage(writer.getImportedPage(inputPdf, pageNumber));
            }
            writer.freeReader(inputPdf);
            inputPdf.close();
            
            // Add Japser disclaimer
            toAddPdf.consolidateNamedDestinations();
            for (int pageNumber = 1; pageNumber <= toAddPdf.getNumberOfPages(); pageNumber++) {
                writer.addPage(writer.getImportedPage(toAddPdf, pageNumber));
            }
            writer.freeReader(toAddPdf);
            toAddPdf.close();
            
            // Set bookmarks
            if (!master.isEmpty()) {
                writer.setOutlines(master);
            }
            document.close();
            writer.close();
            
        } catch (IOException ie) {
            logger.warn(ie.getMessage());
            throw ie;
        } catch (Exception e) {
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw e;
        } finally {
            if (toAddPdf != null) {
                toAddPdf.close();
            }
            if (inputPdf != null) {
                inputPdf.close();
            }
            if (document != null) {
                document.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    /**
     * outDisclaimerPdf
     *
     * MCode(codeType:50)に登録されている仲介業者のディスクレーマを
     * 結合元PDFにあわせのサイズを変更し返却する。
     *
     * @param longSize
     * @param isVertical
     * @return
     * @throws Exception
     */
    private PdfReader outDisclaimerPdf(float longSize, boolean isVertical, String brokerCode) throws Exception {
        
        PdfReader toAddPdf = null;
        PdfReader fromAddPdf = null;
        String disclaimerPdf = StringUtil.EMPTY_STRING;
        ByteArrayOutputStream byteStream = null;
        Rectangle rectangleSize = PageSize.A4;
        float scaleSize = 1.0f;
        String VERTICAL_HORIZONTAL = StringUtil.EMPTY_STRING;
        boolean existsFile = false;
        
        // 対象仲介業者取得 codeType:50 code1->仲介業者コード code2->ファイル名
        List<MCode> mCodeList = mCodeService.getMCodeList(CODE_TYPE, brokerCode);
        if (mCodeList != null && !mCodeList.isEmpty()) {
            
            // 仲介業者毎ディスクレーマ 保管ディレクトリをTB_MED_TEMP_FILE_DIR(FUNC_ID:M7,CAT_ID:0)より取得
            DataList<GetTempFileDirForDocumentCategoryIdModel> dataList = pSiteService
                    .getTempFileDirForDocumentCategoryId(DISCLAIMER_FUNC_ID, DISCLAIMER_CAT_ID);
            if (dataList.getDataList().size() >= 1) {
                disclaimerPdf = dataList.getDataList().get(0).getfile_dir();
            } else {
                // 仲介業者毎ディスクレーマ 保管ディレクトリが存在しない
                return null;
            }
            // 結合元PDFのサイズによりディスクレーマPFDのファイル名を取得
            if (isVertical) {
                VERTICAL_HORIZONTAL = VerticalHorizontal.VERTICAL.getId();
            } else {
                VERTICAL_HORIZONTAL = VerticalHorizontal.HORIZONTAL.getId();
            }
            for (MCode mCode : mCodeList) {
                if (mCode.getCode2().equals(VERTICAL_HORIZONTAL)) {
                    disclaimerPdf += mCode.getName();
                    existsFile = true;
                    break;
                }
            }
            if (!existsFile) {
                // 仲介業者毎ディスクレーマファイル設定が存在し無し
                return null;
            }
            
            // 取得したファイル名によりディスクレーマPFDの存在チェック
            File file = new File(disclaimerPdf);
            if (file.exists()) {
                // 仲介業者別ディスクレーマPDFが存在
                if (longSize < A5_POINT_SIZE_TO) {
                    rectangleSize = PageSize.A5;
                    scaleSize = A5_SCALE_SIZE;
                } else if (longSize < B5_POINT_SIZE_TO) {
                    rectangleSize = PageSize.B5;
                    scaleSize = B5_SCALE_SIZE;
                } else if (longSize < A4_POINT_SIZE_TO) {
                    rectangleSize = PageSize.A4;
                    scaleSize = A4_SCALE_SIZE;
                } else if (longSize < B4_POINT_SIZE_TO) {
                    rectangleSize = PageSize.B4;
                    scaleSize = B4_SCALE_SIZE;
                } else {
                    rectangleSize = PageSize.A3;
                    scaleSize = A3_SCALE_SIZE;
                }
                if (!isVertical) {
                    // サイズの縦横入替
                    rectangleSize = new RectangleReadOnly(rectangleSize.getHeight(), rectangleSize.getWidth());
                }
                try {
                    // 仲介業者別ディスクレーマPDFのサイズを変更しtoAddPdfにStreamで引き継ぐ
                    fromAddPdf = new PdfReader(disclaimerPdf);
                    byteStream = new ByteArrayOutputStream();
                    Document fromDocument = new Document();
                    PdfWriter docWriter = PdfWriter.getInstance(fromDocument, byteStream);
                    fromDocument.open();
                    fromDocument.setPageSize(rectangleSize);
                    
                    for (int pageNumber = 1; pageNumber <= fromAddPdf.getNumberOfPages(); pageNumber++) {
                        fromDocument.newPage();
                        PdfImportedPage page = docWriter.getImportedPage(fromAddPdf, pageNumber);
                        PdfContentByte cb = docWriter.getDirectContent();
                        int pageRotation = fromAddPdf.getPageRotation(1);
                        
                        // ページ結合（90,180,270度の回転及びA4->A5,B5,A4,B4,A3に対応）
                        if (pageRotation == 90) {
                            cb.addTemplate(page, 0, scaleSize * -1, scaleSize, 0, 0, rectangleSize.getHeight());
                        } else if (pageRotation == 180) {
                            cb.addTemplate(page, scaleSize * -1, 0, 0, scaleSize * -1, rectangleSize.getWidth(),
                                    rectangleSize.getHeight());
                        } else if (pageRotation == 270) {
                            cb.addTemplate(page, 0, scaleSize, scaleSize * -1, 0, rectangleSize.getWidth(), 0);
                        } else {
                            cb.addTemplate(page, scaleSize, 0, 0, scaleSize, 0, 0);
                        }
                    }
                    fromDocument.close();
                    byteStream.flush();
                    toAddPdf = new PdfReader(new ByteArrayInputStream(byteStream.toByteArray()));
                } catch (Exception e) {
                    throw e;
                } finally {
                    if (fromAddPdf != null) {
                        fromAddPdf.close();
                    }
                    if (byteStream != null) {
                        byteStream.close();
                    }
                }
            }
            // 対象外の時は初期値(null)
        }
        return toAddPdf;
    }
    
    /**
     * outJasper<br>
     * Create Japser disclaimer
     *
     * @param disclaimer
     * @param numberOfPages
     * @param longSize
     * @param isVertical
     * @return
     * @throws Exception
     */
    private PdfReader outJasper(int numberOfPages, float longSize, boolean isVertical,
            DataList<GetDealerNameAndRegistNoModel> dataList, String brokerCode) throws Exception {
        
        String disclaimer = StringUtil.EMPTY_STRING;
        
        if (brokerCode != null && brokerCode.equals(MP_BROKER_CODE)) {
            // Disclaimer for Money Plaza
            if (isVertical) {
                if (longSize < A5_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_MP_A5_VERTICAL;
                } else if (longSize < B5_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_MP_B5_VERTICAL;
                } else if (longSize < A4_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_MP_A4_VERTICAL;
                } else if (longSize < B4_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_MP_B4_VERTICAL;
                } else {
                    disclaimer = DISCLAIMER_MP_A3_VERTICAL;
                }
            } else {
                if (longSize < A5_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_MP_A5_HORIZONTAL;
                } else if (longSize < B5_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_MP_B5_HORIZONTAL;
                } else if (longSize < A4_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_MP_A4_HORIZONTAL;
                } else if (longSize < B4_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_MP_B4_HORIZONTAL;
                } else {
                    disclaimer = DISCLAIMER_MP_A3_HORIZONTAL;
                }
            }
        } else {
            // Disclaimer for non Money Plaza
            if (isVertical) {
                if (longSize < A5_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_A5_VERTICAL;
                } else if (longSize < B5_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_B5_VERTICAL;
                } else if (longSize < A4_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_A4_VERTICAL;
                } else if (longSize < B4_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_B4_VERTICAL;
                } else {
                    disclaimer = DISCLAIMER_A3_VERTICAL;
                }
            } else {
                if (longSize < A5_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_A5_HORIZONTAL;
                } else if (longSize < B5_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_B5_HORIZONTAL;
                } else if (longSize < A4_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_A4_HORIZONTAL;
                } else if (longSize < B4_POINT_SIZE_TO) {
                    disclaimer = DISCLAIMER_B4_HORIZONTAL;
                } else {
                    disclaimer = DISCLAIMER_A3_HORIZONTAL;
                }
            }
        }
        try {
            // Create a form from compiled form layout file (. Jasper)
            JasperPrint jasperPrint = JasperFillManager.fillReport(this.createPreCompiledReport(disclaimer),
                    this.createParameters(brokerCode),
                    this.createDataSource(disclaimer, numberOfPages, dataList, brokerCode));
            
            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
            
            return new PdfReader(new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Compiled form layout generation
     * @param disclaimer (Compiled 「*.jasper」 / Template XML 「*.jrxml」)
     * @return JasperReport Compiled form layout
     * @throws JRException
     * @throws IOException
     */
    private JasperReport createPreCompiledReport(String disclaimer) throws JRException, IOException {
        
        Resource resource = resourceLoader.getResource(JASPER_PATH + disclaimer);
        if (disclaimer.endsWith(".jrxml")) {
            // Compile template XML
            return JasperCompileManager.compileReport(resource.getFile().getAbsolutePath());
        } else {
            // Load compiled form layout
            return (JasperReport) JRLoader.loadObject(resource.getFile());
        }
    }
    
    /**
     * Create parameters to be set in the form layout
     * @param paramName
     * @param paramSource
     * @return Parameters to be set in the form layout
     * @throws IOException
     */
    private Map<String, Object> createParameters(String brokerCode) throws IOException {
        
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        
        if (brokerCode != null && brokerCode.equals(MP_BROKER_CODE)) {
            Resource resource = resourceLoader.getResource(JASPER_PATH + MPLOGO_FILE);
            parameters.put(MPLOGO, resource.getInputStream());
        }
        
        return parameters;
    }
    
    /**
     * Create data source to bind to form layout
     * @param brokerCode
     * @return Data source to bind to form layout
     * @throws Exception
     */
    private JRDataSource createDataSource(String disclaimer, int numberOfPages,
            DataList<GetDealerNameAndRegistNoModel> dataList, String brokerCode) throws Exception {
        
        List<GetDealerNameAndRegistNoModel> getDealerNameAndRegistNoList = new ArrayList<GetDealerNameAndRegistNoModel>();
        GetDealerNameAndRegistNoModel dealerNameAndRegistNo = new GetDealerNameAndRegistNoModel();
        
        DataList<GetTbMedDisclaimerModel> dataListDisclaimer = new DataList<GetTbMedDisclaimerModel>();
        
        if (dataList.getTotalSize() > 0) {
            dealerNameAndRegistNo = dataList.getDataList().get(0);
        } else {
            //TODO Theoretically unreached
            dealerNameAndRegistNo.setDealerName("商号　未登録[" + brokerCode + "]");
            dealerNameAndRegistNo.setRegistNo("登録番号　未登録[" + brokerCode + "]");
        }
        if (brokerCode != null && brokerCode.equals(MP_BROKER_CODE)) {
            dataListDisclaimer = pSiteService.getTbMedDisclaimer(DISCLAIMER_ID_MP);
            for (GetTbMedDisclaimerModel tbMedDisclaimer : dataListDisclaimer.getDataList()) {
                switch (tbMedDisclaimer.getItemName()) {
                case TYPE2_BROKER:
                    dealerNameAndRegistNo.setType2Broker(tbMedDisclaimer.getContent());
                    break;
                case BROKER:
                    dealerNameAndRegistNo.setBroker(tbMedDisclaimer.getContent());
                    break;
                case BANK_AGENT:
                    dealerNameAndRegistNo.setBankAgent(tbMedDisclaimer.getContent());
                    break;
                case MONEYLENDER:
                    dealerNameAndRegistNo.setMoneylender(tbMedDisclaimer.getContent());
                    break;
                case IMPORTANT_POINTS:
                    dealerNameAndRegistNo.setImportantPoints(tbMedDisclaimer.getContent());
                    break;
                case ABOUT_FINANCIAL_PRODUCT_BROKERAGE:
                    dealerNameAndRegistNo.setAboutFinancialProductBrokerage(tbMedDisclaimer.getContent());
                    break;
                case ABOUT_BANK_AGENT:
                    dealerNameAndRegistNo.setAboutBankAgent(tbMedDisclaimer.getContent());
                    break;
                case ABOUT_INSURANCE_AGENCY:
                    dealerNameAndRegistNo.setAboutInsuranceAgency(tbMedDisclaimer.getContent());
                    break;
                }
            }
        } else {
            dataListDisclaimer = pSiteService.getTbMedDisclaimer(DISCLAIMER_ID_ETC);
            for (GetTbMedDisclaimerModel tbMedDisclaimer : dataListDisclaimer.getDataList()) {
                switch (tbMedDisclaimer.getItemName()) {
                case MSG1:
                    dealerNameAndRegistNo.setMsg1(tbMedDisclaimer.getContent());
                    break;
                case MSG2:
                    dealerNameAndRegistNo.setMsg2(tbMedDisclaimer.getContent());
                    break;
                case INSTRUMENTS_FIRMS:
                    dealerNameAndRegistNo.setInstrumentsFirms(tbMedDisclaimer.getContent());
                    break;
                case REGISTRATION_NUMBER:
                    dealerNameAndRegistNo.setRegistrationNumber(tbMedDisclaimer.getContent());
                    break;
                case MEMBER_ASSOCIATIONS:
                    dealerNameAndRegistNo.setMemberAssociations(tbMedDisclaimer.getContent());
                    break;
                }
            }
        }
        dealerNameAndRegistNo.setPageNumber(String.format("- %d -", numberOfPages + 1));
        getDealerNameAndRegistNoList.add(dealerNameAndRegistNo);
        
        JRDataSource dataSource = new JRBeanCollectionDataSource(getDealerNameAndRegistNoList);
        
        return dataSource;
    }
    
    /**
     * isDisclaimerNotAdded<br>
     * Determine whether a password is set in the download file
     * @param fileName
     * @param fileGrp
     * @return true:password is set or is not PFD File / false:password is not set
     * @throws IOException
     */
    private static boolean isDisclaimerNotAdded(String fileName) throws IOException {
        
        boolean resulet = true;
        File file = new File(fileName);
        if (MimeUtil.getMimeTypes(file).contains(MIME_TYPE_PDF)) {
            // target file is PDF
            PdfReader pdfReader = null;
            try {
                pdfReader = new PdfReader(fileName);
                // User is not set
                if (pdfReader.isOpenedWithFullPermissions()) {
                    // User is not set & Owner is not set
                    resulet = false;
                } else {
                    // User is not set & Owner is set
                    resulet = true;
                }
            } catch (BadPasswordException e) {
                // User is set & Owner is ?
                resulet = true;
            } finally {
                if (pdfReader != null) {
                    pdfReader.close();
                }
            }
        } else {
            // target file is not PDF
            resulet = true;
        }
        return resulet;
    }
    
    /**
     * postDownload
     * ファイルダウンロード共通
     *
     * @param fileName String フルパスファイル名
     * @param brokerCode String 対象仲介業者コード
     * @param isDisclaimer String ディスクレーマ連結有無 "0":無/以外:有
     * @param sessionId セッションID
     * @param userId ユーザID
     * @return 出力ファイルパス
     *
     * @throws Exception
     */
    public String postDownload(String fileName, String brokerCode, String isDisclaimer, String sessionId, String userId)
            throws Exception {
        
        String outputFileDir = "/opt/download/pdfTemporary/";
        String outputFileName = outputFileDir + String.format(TEMPORARY_PDF_FILE_NAME,
                DateTimeFormatter.ofPattern(PDF_TEMPORARY_DATE_PATTERN).format(LocalDateTime.now()), sessionId, userId);
        
        try (FileOutputStream outputStream = new FileOutputStream(new File(outputFileName))) {
            File inputFile = new File(fileName);
            if (!inputFile.exists() || inputFile.isDirectory()) {
                throw new IllegalArgumentException(fileName + " was not found on the server");
            }
            try (FileInputStream inputStream = new FileInputStream(inputFile)) {
                if (StringUtil.isNullOrEmpty(brokerCode) || isDisclaimerNotAdded(fileName)
                        || "0".equals(isDisclaimer)) {
                    IOUtils.copy(inputStream, outputStream);
                } else {
                    DataList<GetDealerNameAndRegistNoModel> dataList = pSiteService
                            .getDealerNameAndRegistNo(brokerCode);
                    if (isBroker(dataList)) {
                        addDisclaimer(inputStream, outputStream, dataList, brokerCode);
                    } else {
                        IOUtils.copy(inputStream, outputStream);
                    }
                }
            }
        }
        
        deleteTempFile(outputFileDir);
        
        return outputFileName;
    }
    
    /**
     * 一時ファイルの削除を行う.
     * 
     * CSVダウンロードと同じ動作仕様となる想定のため、BaseController.deleteTempFileを参考にして実装。
     * 
     * @param outputFileDir 一時ファイル出力パス
     */
    private void deleteTempFile(String outputFileDir) throws Exception {
        
        LocalDate yesterday = LocalDate.now().minusDays(1);
        for (File file : new File(outputFileDir).listFiles()) {
            if (yesterday.compareTo(LocalDateTime
                    .ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault()).toLocalDate()) > 0) {
                file.delete();
                logger.warn("FileDownloadUtil.deleteTempFile Deleted : {}", file.getName());
            }
        }
    }

    /**
    * 元のファイルを削除する
    *
    * @param filePath {@code String }
    * @param fileName {@code String }
    * @return 成功:True,失敗:False {@code Boolean}
    * @throws Exception ファイル操作処理で例外が発生した場合
    */
    public Boolean deleteFromFileSyst(String filePath, String fileName) {
        try {
            File f = new File(filePath + fileName);
            // ファイルが存在すれば削除
            if (f.exists() && !f.isDirectory()) {
                if (f.delete()) {
                    return true;
                }
            }
        } catch (Exception e) {
        	logger.error("Could not delete the file " + fileName + "from the server");
            throw e;
        }
        return false;
    }
    
}

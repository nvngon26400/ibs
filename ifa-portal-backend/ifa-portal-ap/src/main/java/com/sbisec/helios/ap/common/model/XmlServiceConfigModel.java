package com.sbisec.helios.ap.common.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.Getter;

public class XmlServiceConfigModel {
    
    private final static String FIND_START = "<import resource=\"";
    private final static String FIND_END = "\"/>";
    private final static String REMOVE_START = "<!--";
    private final static String REMOVE_END = "-->";

	private String INPUT_FILE_NAME = "service-config.xml";

	@Getter
	private XmlServiceTopElement services;

	public XmlServiceConfigModel() {
		try {
			this.services = getXmlStringFromSettingFile();
		} catch (Exception e) {
			this.services = null;
		}
	}

	private XmlServiceTopElement getXmlStringFromSettingFile() throws FileNotFoundException, IOException {
		URL url = this.getClass().getClassLoader().getResource(INPUT_FILE_NAME);
		String fullFileName = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8.toString());
		File file = new File(fullFileName);
		
		String xml = inputStreamToString(new FileInputStream(file));
		List<String> importFilePathList = new ArrayList<>();
		importFilePathList = findImportFilePathList(xml);
		
		XmlServiceTopElement value = new XmlServiceTopElement();
		boolean firstFlg = true;
		
		for (String importFilePath : importFilePathList) {
		    URL importUrl = this.getClass().getClassLoader().getResource(importFilePath);
	        String importfullFileName = URLDecoder.decode(importUrl.getFile(), StandardCharsets.UTF_8.toString());
		    File importFile = new File(importfullFileName);
		    XmlMapper xmlMapper = new XmlMapper();
		    String importXml = inputStreamToString(new FileInputStream(importFile));
		    XmlServiceTopElement importValue = xmlMapper.readValue(importXml, XmlServiceTopElement.class);
	        if (firstFlg) {
                value.setServices(importValue.getServices());
                firstFlg = false;
	        } else {
	            List<XmlServiceModel> tmp = value.getServices();
	            tmp.addAll(importValue.getServices());
	            value.setServices(tmp);    
	        }
		}
		return value;
	}

	private static String inputStreamToString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}
	
	private static List<String> findImportFilePathList(String text) {
        
        // 削除対象の正規表現パターンを構築
        String removeRegex = REMOVE_START + ".*?" + REMOVE_END;
        Pattern removePattern = Pattern.compile(removeRegex);
        Matcher removeMatcher = removePattern.matcher(text);

        // コメント部分を削除
        String editedText = removeMatcher.replaceAll("");

        // 検索対象の正規表現パターンを構築
        String findRegex = FIND_START + "(.*?)" + FIND_END;
        Pattern findPattern = Pattern.compile(findRegex);
        Matcher findMatcher = findPattern.matcher(editedText);
        
        // 一致する全ての文字列を見つけてリストに追加
        List<String> substrings = new ArrayList<>();
        while (findMatcher.find()) {
            // group(1) は括弧内にマッチした部分{
            substrings.add(findMatcher.group(1));
        }

        return substrings;
    }
}



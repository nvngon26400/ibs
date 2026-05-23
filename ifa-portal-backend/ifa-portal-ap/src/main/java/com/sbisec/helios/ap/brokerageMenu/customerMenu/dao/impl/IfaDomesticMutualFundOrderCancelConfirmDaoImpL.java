package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticMutualFundOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaDomesticMutualFundOrderCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0401-04_1
 * 画面名：国内投信注文取消確認
 *
 * @author SCSK
 *     2023/11/27 新規作成
 */
@Component
public class IfaDomesticMutualFundOrderCancelConfirmDaoImpL extends RowSelectableDao
        implements IfaDomesticMutualFundOrderCancelConfirmDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticMutualFundOrderCancelConfirmDaoImpL.class);
    
    @Autowired
    private IfaDomesticMutualFundOrderCancelConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：注文取消前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel
     *
     * @param req リクエストパラメータ
     * @return レスポンス（resModel）：サービス側で使うのはIFA注文番号とIFA注文サブ番号だけ
     * @exception Exception 注文取消前の注文登録処理で例外が発生した場合
     */
    public IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel insertIfaDomesticMutualFundOrderCancelConfirmSql001(
            IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel req) throws Exception {
        
        IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel resModel = null;
        
        //サブクエリ①の実行
        
        List<IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel> resModelList = null;
        
        try {
            resModelList = mapper.selectIfaDomesticMutualFundOrderCancelConfirmSql00101(req);
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw e;
        }
        
        int insertCnt = 0;
        if (resModelList == null || resModelList.size() == 0) {
            
            // シーケンスオブジェクトから新規IFA注文番号の取得
            try {
                resModelList = mapper.selectIfaDomesticMutualFundOrderCancelConfirmSql00102(req);
            } catch (Exception e) {
                LOGGER.error("Exception occured.");
                LOGGER.info("Exception occured.", e);
                throw e;
            }
            
            // reqにIFA注文サブ番号"1"を設定
            // resModelには登録時のIFA注文サブ番号を設定する必要があるためresModelにも"1"をセット。
            resModel = resModelList.get(0);
            resModel.setIfaOrderSubNo("1");
            req.setIfaOrderNo(resModel.getIfaOrderNo());
            

            // リクエスト.口数の編集
            // ■ 取引種別= '0' 購入（一般）　または
            //            '7' 解約（一般）または
            //            '3' 買取（一般）または　
            //           （取引種別= '8' 解約（累投）または　'4' 買取（累投）または　'12' 買取（定率）または　'13' 買取（期間）または　'14' 解約（定率）または　'15' 解約（期間））　かつ　購入解約方法=2:口数指定の場合
            //           セット
            // ■ 上記以外
            //          　NULL
            if (!(StringUtils.equals("0", req.getTradeCd()) 
                    || StringUtils.equals("7", req.getTradeCd())
                    || StringUtils.equals("3", req.getTradeCd())
                    || ((StringUtils.equals("8", req.getTradeCd()) || StringUtils.equals("4", req.getTradeCd())
                            || StringUtils.equals("12", req.getTradeCd()) || StringUtils.equals("13", req.getTradeCd())
                            || StringUtils.equals("14", req.getTradeCd()) || StringUtils.equals("15", req.getTradeCd()))
                            && StringUtils.equals("2", req.getBuyCancelMethod())))) {
                req.setUnit(null);
            }
            //リクエスト.金額   の編集
            // ■ （取引種別= '8' 解約（累投）または　'4' 買取（累投）または　'12' 買取（定率）または　'13' 買取（期間）または　'14' 解約（定率）または　'15' 解約（期間））かつ　　購入解約方法=1:金額指定　または
            //         取引種別= '1' 購入（累投）または　'2' 購入（積立）または　'5' 買取（定額）または　'9' 解約（定額）または　'11' 購入（カード積立）
            //         の場合　セット
            // ■ 上記以外
            //         NULL
            if (!(((StringUtils.equals("8", req.getTradeCd()) || StringUtils.equals("4", req.getTradeCd())
                    || StringUtils.equals("12", req.getTradeCd()) || StringUtils.equals("13", req.getTradeCd())
                    || StringUtils.equals("14", req.getTradeCd()) || StringUtils.equals("15", req.getTradeCd()))
                        && StringUtils.equals("1", req.getBuyCancelMethod()))
                    || StringUtils.equals("1", req.getTradeCd()) || StringUtils.equals("2", req.getTradeCd())
                    || StringUtils.equals("5", req.getTradeCd()) || StringUtils.equals("9", req.getTradeCd())
                    || StringUtils.equals("11", req.getTradeCd()))) {
                req.setAmount(null);
            }
            
            try {
                insertCnt = mapper.insertIfaDomesticMutualFundOrderCancelConfirmSql00101(req);
            } catch (Exception e) {
                LOGGER.error("Exception occured.");
                LOGGER.info("Exception occured.", e);
                throw e;
            }
        } else {
            resModel = resModelList.get(0);
            
            // IFA注文番号
            req.setIfaOrderNo(resModel.getIfaOrderNo());
            // IFA注文サブ番号
            req.setIfaOrderSubNo(resModel.getIfaOrderSubNo());
            // 部店コード
            req.setButenCode(resModel.getButenCode());
            // 口座番号
            req.setAccountNumber(resModel.getAccountNumber());
            // 顧客コード
            req.setCustomerId(resModel.getCustomerId());
            // 特定口座区分
            req.setSpecificAccount(resModel.getSpecificAccount());
            // リクエスト.口数の編集
            // ■ 取引種別= '0' 購入（一般）　または
            //            '7' 解約（一般）または
            //            '3' 買取（一般）または　
            //           （取引種別= '8' 解約（累投）または　'4' 買取（累投））　かつ　購入解約方法=2:口数指定の場合
            //           セット
            // ■ 上記以外
            //          　NULL
            if (!(StringUtils.equals("0", req.getTradeCd()) 
                    || StringUtils.equals("7", req.getTradeCd())
                    || StringUtils.equals("3", req.getTradeCd())
                    || ((StringUtils.equals("8", req.getTradeCd()) || StringUtils.equals("4", req.getTradeCd()))
                            && StringUtils.equals("2", req.getBuyCancelMethod())))) {
                req.setUnit(null);
            }
            //リクエスト.金額   の編集
            // ■ （取引種別= '8' 解約（累投）または　'4' 買取（累投））かつ　　購入解約方法=1:金額指定　または
            //         取引種別= '1' 購入（累投）
            //         の場合　セット
            // ■ 上記以外
            //         NULL
            if (!(((StringUtils.equals("8", req.getTradeCd()) || StringUtils.equals("4", req.getTradeCd()))
                        && StringUtils.equals("1", req.getBuyCancelMethod()))
                    || StringUtils.equals("1", req.getTradeCd()))) {
                req.setAmount(null);
            }
            // 分配金受取方法
            req.setDistributionReceiveMethod(resModel.getDistributionReceiveMethod());
            // 目論見書チェック区分
            req.setDispatchId(resModel.getDispatchId());
            // ポイント種別
            req.setPointType(resModel.getPointType());
            // ポイント利用
            req.setPointFlg(resModel.getPointFlg());
            // レバレッジ投資信託
            req.setLeverageKbn(resModel.getLeverageKbn());
            // 乗換勧誘
            req.setNorikaeKanyuKbn(resModel.getNorikaeKanyuKbn());
            // 同一通貨/同一国の乗換
            req.setDouitsuTukaKuniKbn(resModel.getDouitsuTukaKuniKbn());
            // 他社間投信乗換勧誘
            req.setTashaNorikaeKbn(resModel.getTashaNorikaeKbn());
            // 短期売却確認
            req.setTankiSellKbn(resModel.getTankiSellKbn());
            // 償還前売却確認
            req.setShokanMaeKbn(resModel.getShokanMaeKbn());
            // 勧誘区分
            req.setKanyuKbn(resModel.getKanyuKbn());
            // 受注方法
            req.setJutyuKbn(resModel.getJutyuKbn());
            // 目論見書の交付方法
            req.setMokuromiKoufuKbn(resModel.getMokuromiKoufuKbn());
            // 利益相反可能性の説明
            req.setConflictOfInterestExplain(resModel.getConflictOfInterestExplain());
            // 確認項目.目論見書補完書面の確認
            req.setCheckMokuromi(resModel.getCheckMokuromi());
            // 確認項目.窓空きファンドの注意事項に同意
            req.setCheckMadoAki(resModel.getCheckMadoAki());
            // 確認項目.費用について説明済
            req.setCheckHiyou(resModel.getCheckHiyou());
            // 確認項目.複数取引業者での手数料等明示済
            req.setCheckFukusu(resModel.getCheckFukusu());
            // アラート内容確認.コンプラチェックワーニング確認
            req.setCheckCompWrnAlert(resModel.getCheckCompWrnAlert());
            // 資金性格区分
            req.setShikinSeikakuKbn(resModel.getShikinSeikakuKbn());
            // ユーザID
            req.setUserId(resModel.getUserId());
            
            try {
                insertCnt = mapper.insertIfaDomesticMutualFundOrderCancelConfirmSql00102(req);
            } catch (Exception e) {
                LOGGER.error("Exception occured.");
                LOGGER.info("Exception occured.", e);
                throw e;
            }

            //　resModelの結果が1件以上のときは、返却するIFA注文サブ番号を更新（+1）
            int newIfaOrderSubNo = Integer.parseInt(req.getIfaOrderSubNo()) + 1;
            resModel.setIfaOrderSubNo(Integer.toString(newIfaOrderSubNo));
        }
        
        //インサートに失敗した場合 nullを返却する。
        if (insertCnt == 0) {
            resModel = null;
        }

        
        return resModel;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：注文取消後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消後の注文更新処理で例外が発生した場合
     */
    public int updateIfaDomesticMutualFundOrderCancelConfirmSql002(
            IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel req) throws Exception {
        
        try {
            return mapper.updateIfaDomesticMutualFundOrderCancelConfirmSql002(req);
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw e;
        }
    }

    /**
     * SQLID：Sql002
     * SQL名：注文取消後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 注文取消後の注文更新処理で例外が発生した場合
     */
    public int updateIfaDomesticMutualFundOrderCancelConfirmSql002b(
            IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel req) throws Exception {
        
        try {
            return mapper.updateIfaDomesticMutualFundOrderCancelConfirmSql002b(req);
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw e;
        }
    }
    
}

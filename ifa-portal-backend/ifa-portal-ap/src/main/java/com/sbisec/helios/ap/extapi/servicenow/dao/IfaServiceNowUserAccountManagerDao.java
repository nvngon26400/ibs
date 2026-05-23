package com.sbisec.helios.ap.extapi.servicenow.dao;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaA005VerifyUserModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaA006MedUsersModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaA006MedUsrsPrivModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaTbMedUserModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaTbMedUsersPriv;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA006RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA014RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowUserDto;

/**
 * IfaServiceNowUserAccountManagerDao
 *
 * @author SCSK
 */
public interface IfaServiceNowUserAccountManagerDao {
    
    /**
     * ログインID登録
     * -> Horusユーザー情報(TB_MED_USERS)テーブルからユーザー情報を検索する
     */
    public List<IfaTbMedUserModel> selectA005UserByUserId(String userId) throws Exception;
    
    /**
     * ログインID登録
     * -> Horusユーザー情報(TB_MED_USERS)テーブルを登録する
     */
    public int insertA005MedUser(IfaTbMedUserModel req) throws Exception;
    
    /**
     * ログインID登録
     * -> Horusユーザー権限情報(TB_MED_USERS_PRIV)テーブルを登録する
     */
    public int insertA005UserPriv(IfaTbMedUsersPriv req) throws Exception;
    
    /**
     * ログインID登録
     * -> 認証ユーザー情報(TB_MED_VERIFY_USERS)テーブルを登録する
     */
    public int insertA005VerifyUser(IfaA005VerifyUserModel req) throws Exception;
    
    /**
     * 申請情報項目一覧を取得
     * -> テーブル「Horusユーザー情報」からユーザー情報を取得する(支店情報を含む)
     * -> テーブル「Horusユーザー権限情報」からユーザー情報を取得する(支店情報を含む)
     */
    public List<IfaA006MedUsersModel> selectA006User(String userId) throws Exception;

    //TODO: delete
    /**
     * 申請情報項目一覧を取得
     * -> テーブル「Horusユーザー情報」からユーザー情報を取得する(支店情報を含む)
     */
    public List<IfaA006MedUsersModel> selectA006MedUsers(
            IfaServiceNowUserAccountManagerA006RequestDto req) throws Exception;

    /**
     * 申請情報項目一覧を取得
     * -> テーブル「Horusユーザー権限情報」からユーザー情報を取得する(支店情報を含む)
     */
    public List<IfaA006MedUsrsPrivModel> selectA006MedUsersPriv(
            IfaServiceNowUserAccountManagerA006RequestDto req) throws Exception;
    
    /**
     * ログインID更新
     * -> Horusユーザー情報(TB_MED_USERS)テーブルを更新する
     */
    public int updateA007MedUser(IfaTbMedUserModel model) throws Exception;
    
    /**
     * ユーザ&&利用できるメニューを削除
     * -> テーブル「Cordysユーザーとメニューマッピング情報_新」の削除
     */
    public int deleteA008GovMenu(String userId) throws Exception;
    
    /**
     * ユーザ&&利用できるメニューを削除
     * -> テーブル「Horusユーザー権限情報」の削除
     */
    public int deleteA008UserPriv(String userId) throws Exception;
    
    /**
     * ユーザ&&利用できるメニューを削除
     * -> テーブル「Horusユーザー情報」の削除
     */
    public int deleteA008MedUsers(String userId) throws Exception;
    
    /**
     * ユーザ&&利用できるメニューを削除
     * -> テーブル「認証ユーザー情報」の削除
     */
    public int deleteA008VerifyUsers(String userId) throws Exception;

     /**
     * ログインID検索
     * -> テーブル「Horusユーザー権限情報」からユーザー情報を取得する(支店情報を含む)
     */
    public List<IfaServiceNowUserDto> selectA014(
            IfaServiceNowUserAccountManagerA014RequestDto req) throws Exception;
    
}

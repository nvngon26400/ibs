package com.sbisec.helios.ap.extapi.servicenow.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.extapi.servicenow.dao.IfaServiceNowUserAccountManagerDao;
import com.sbisec.helios.ap.extapi.servicenow.dao.mapper.IfaServiceNowUserAccountManagerMapper;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaA005VerifyUserModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaA006MedUsersModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaA006MedUsrsPrivModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaTbMedUserModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaTbMedUsersPriv;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA006RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA014RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowUserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * IfaServiceNowUserAccountManagerDaoImp
 *
 * @author SCSK
 */
@Component
public class IfaServiceNowUserAccountManagerDaoImp extends RowSelectableDao
        implements IfaServiceNowUserAccountManagerDao {
    
    @Autowired
    private IfaServiceNowUserAccountManagerMapper mapper;
    
    @Override
    public List<IfaTbMedUserModel> selectA005UserByUserId(String userId) throws Exception {
        
        return mapper.selectA005UserByUserId(userId);
    }
    
    @Override
    public int insertA005MedUser(IfaTbMedUserModel req) throws Exception {
        
        return mapper.insertA005MedUsers(req);
    }
    
    @Override
    public int insertA005UserPriv(IfaTbMedUsersPriv req) throws Exception {
        
        return mapper.insertA005UserPriv(req);
    }
    
    @Override
    public int insertA005VerifyUser(IfaA005VerifyUserModel req) throws Exception {
        
        return mapper.insertA005VerifyUser(req);
    }
    
    @Override
    public List<IfaA006MedUsersModel> selectA006User(String userId) throws Exception {
        
        return mapper.selectA006User(userId);
    }

    @Override
    public List<IfaA006MedUsersModel> selectA006MedUsers(IfaServiceNowUserAccountManagerA006RequestDto req)
            throws Exception {
        
        return mapper.selectA006MedUsers(req);
    }
    
    @Override
    public List<IfaA006MedUsrsPrivModel> selectA006MedUsersPriv(IfaServiceNowUserAccountManagerA006RequestDto req)
            throws Exception {
        
        return mapper.selectA006MedUsersPriv(req);
    }
    
    @Override
    public int updateA007MedUser(IfaTbMedUserModel req) throws Exception {
        
        return mapper.updateA007MedUser(req);
    }
    
    @Override
    public int deleteA008GovMenu(String userId) throws Exception {
        
        return mapper.deleteA008GovMenu(userId);
    }
    
    @Override
    public int deleteA008UserPriv(String userId) throws Exception {
        
        return mapper.deleteA008UserPriv(userId);
    }
    
    @Override
    public int deleteA008MedUsers(String userId) throws Exception {
        
        return mapper.deleteA008MedUsers(userId);
    }
    
    @Override
    public int deleteA008VerifyUsers(String userId) throws Exception {
        
        return mapper.deleteA008VerifyUsers(userId);
    }

    @Override
    public List<IfaServiceNowUserDto> selectA014(IfaServiceNowUserAccountManagerA014RequestDto req)
            throws Exception {
        
        return mapper.selectA014(req);
    }
}

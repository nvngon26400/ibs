package com.sbisec.helios.ap.extapi.servicenow.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.extapi.servicenow.dao.IfaServiceNowMenuAndAclManagerDao;
import com.sbisec.helios.ap.extapi.servicenow.dao.mapper.IfaServiceNowMenuAndAclManagerMapper;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA010RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA011RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA012RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowMenuDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * IfaServiceNowUserAccountManagerDaoImp
 *
 * @author SCSK
 */
@Component
public class IfaServiceNowMenuAndAclManagerDaoImp extends RowSelectableDao
        implements IfaServiceNowMenuAndAclManagerDao {
    
    @Autowired
    private IfaServiceNowMenuAndAclManagerMapper mapper;
    
    @Override
    public List<IfaServiceNowMenuDto> selectA010Menu(IfaServiceNowMenuAndAclManagerA010RequestDto req)
            throws Exception {
        
        return mapper.selectA010Menu(req);
    }
    
    @Override
    public int deleteA011MenuByUserId(IfaServiceNowMenuAndAclManagerA011RequestDto req) throws Exception {
        
        return mapper.deleteA011MenuByUserId(req);
    }
    

    @Override
    public int deleteA012MenuByUserIdAndMenuIdAndPrivId(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq)
            throws Exception {
        
        return mapper.deleteA012MenuByUserIdAndMenuIdAndPrivId(dtoReq);
    }

    @Override
    public int insertA012Menu(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq) throws Exception {
        
        return mapper.insertA012Menu(dtoReq);
    }
    
    @Override
    public int deleteA012OldMenuByUserId(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq)
            throws Exception {
        
        return mapper.deleteA012OldMenuByUserId(dtoReq);
    }

    @Override
    public int insertA012OldMenu(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq) throws Exception {
        
        return mapper.insertA012OldMenu(dtoReq);
    }
    
    @Override
    public List<IfaServiceNowMenuDto> selectA013AvailableMenu(IfaServiceNowMenuAndAclManagerA013RequestDto dtoReq)
            throws Exception {
        
        return mapper.selectA013AvailableMenu(dtoReq);
    }

    
}

package com.sbisec.helios.ap.testtool.service;

import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct024Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct034Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct035Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct037Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct039Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct024Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct034Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct035Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct037Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct039Dto;
import com.sbisec.helios.ap.service.Service;

/**
 * 共通関数スタブテスト用サービス
 * 
 * @author base 熊
 */
public interface FunctionTestService extends Service {

    OutputFct001Dto doFct001(InputFct001Dto input) throws Exception;
    
    OutputFct004Dto doFct004(InputFct004Dto input) throws Exception;
    
    OutputFct020Dto doFct020(InputFct020Dto input);
    
    OutputFct024Dto doFct024(InputFct024Dto input);
    
    OutputFct027Dto doFct027(InputFct027Dto input);
    
    OutputFct028Dto doFct028(InputFct028Dto input) throws Exception;

    OutputFct030Dto doFct030(InputFct030Dto input) throws Exception;

    OutputFct034Dto doFct034(InputFct034Dto input) throws Exception;
    
    OutputFct035Dto doFct035(InputFct035Dto input) throws Exception;
    
    OutputFct032Dto doFct032(InputFct032Dto input);
    
    OutputFct033Dto doFct033(InputFct033Dto input) throws Exception;
    
    OutputFct031Dto doFct031(InputFct031Dto input) throws Exception;
    
    OutputFct037Dto doFct037(InputFct037Dto input);
    
    OutputFct038Dto doFct038(InputFct038Dto input);
    
    OutputFct039Dto doFct039(InputFct039Dto input) throws Exception;
    
    OutputFct006Dto doFct006(InputFct006Dto input);
}

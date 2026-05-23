package com.sbisec.helios.ap.athena.protocol.exchange.master;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ListOperatorScopesRes {
    
    /**オペレータ権限*/
    List<String> operatorScopes = new ArrayList<String>();
    
}

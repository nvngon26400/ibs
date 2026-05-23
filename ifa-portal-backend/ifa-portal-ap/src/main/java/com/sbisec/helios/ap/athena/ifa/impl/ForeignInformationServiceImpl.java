package com.sbisec.helios.ap.athena.ifa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignInformationService;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketReq;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashReq;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesReq;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp;
import com.sbisec.helios.ap.athena.service.CometInformationService;
import com.sbisec.helios.ap.athena.utils.RequestUtil;

@Service
public class ForeignInformationServiceImpl implements ForeignInformationService {

	@Autowired
	CometInformationService cometInformationService;

	@Override
	public CreateMarketPriceTicketResp createMarketPriceTicket(String butenCode, String accountNo, String ip,
			String xAppUserAgent, String countryCode) throws Exception {

		CreateMarketPriceTicketReq createMarketPriceTicketReq = new CreateMarketPriceTicketReq();
		createMarketPriceTicketReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
		createMarketPriceTicketReq.getHeader().setIp(ip);
		createMarketPriceTicketReq.getHeader().setAppUserAgent(xAppUserAgent);
		createMarketPriceTicketReq.getParameter().setCountryCode(countryCode);
		return cometInformationService.createMarketPriceTicket(createMarketPriceTicketReq);
	}

	@Override
	public ListMarketPricesResp listMarketPrices(String hashToken, String countryCode, String[] rics)
			throws Exception {

		ListMarketPricesReq listMarketPricesReq = new ListMarketPricesReq();
		listMarketPricesReq.getHeader().setHash_token(hashToken);
		listMarketPricesReq.getParameter().setCountryCode(countryCode);
		listMarketPricesReq.getParameter().setRics(rics);
		return cometInformationService.listMarketPrices(listMarketPricesReq);
	}

	@Override
	public GetMarketPriceHashResp getMarketPriceHash(String butenCode, String accountNo, String ticket,
			String countryCode) throws Exception {

		GetMarketPriceHashReq getMarketPriceHashReq = new GetMarketPriceHashReq();
		getMarketPriceHashReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
		getMarketPriceHashReq.getHeader().setTicket(StringUtil.isNullOrEmpty(ticket) ? StringUtil.EMPTY_STRING : ticket);
		getMarketPriceHashReq.getParameter().setCountryCode(countryCode);
		return cometInformationService.getMarketPriceHash(getMarketPriceHashReq);
	}
}

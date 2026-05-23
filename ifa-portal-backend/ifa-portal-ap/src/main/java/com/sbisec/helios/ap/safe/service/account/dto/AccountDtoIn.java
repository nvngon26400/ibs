package com.sbisec.helios.ap.safe.service.account.dto;

import com.sbisec.helios.ap.safe.service.common.dto.DtoIn;

/**
 * AccountServiceのInputのDtoベース
 */
public abstract class AccountDtoIn extends DtoIn {

    /**
     * コンストラクタ
     * @param id APIアクセス時のid
     */
    public AccountDtoIn(String id) {
        super("account", "1.0.0", id);
    }
}

package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form;

import java.util.List;
import lombok.Data;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/11/11
 */

@Data
public class IfaReleaseNoteRegisterA006ApiRequest {

    /** 内容リスト. */
    List<ContentItem> contentItemList;

}

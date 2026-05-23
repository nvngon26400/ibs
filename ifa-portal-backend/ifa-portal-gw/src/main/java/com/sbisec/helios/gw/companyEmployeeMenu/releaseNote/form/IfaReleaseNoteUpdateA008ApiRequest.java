package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form;

import java.util.List;
import lombok.Data;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/11/12
 */

@Data
public class IfaReleaseNoteUpdateA008ApiRequest {

    /** 内容リスト. */
    List<ContentItem> contentItemList;

}

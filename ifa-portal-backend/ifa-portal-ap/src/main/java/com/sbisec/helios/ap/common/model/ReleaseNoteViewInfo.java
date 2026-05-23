package com.sbisec.helios.ap.common.model;

import java.util.Date;

import com.sbibits.earth.model.ModelBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * リリースノート閲覧情報取得
 * 2025/11/10 新規作成
 *
 * @author 大連 葉
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class ReleaseNoteViewInfo extends ModelBase{

    /** リリースノート最新日時 */
    private Date releaseNoteLatestTime;

    /** 最終閲覧日時 */
    private Date lastReadTime;

    /** 次回表示フラグ */
    private String nextDispFlg;

}

package com.sbisec.helios.ap.wholePortal.dto;

import java.util.List;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA021ResponseDto {

    /** お知らせリスト */
    private List<IfaWholePortalHomeA021ResponseDtoNotification> notificationList;

}

package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.sbibits.earth.util.StringUtil;

/**
 * SUB0512 リリースノートアップロード
 *
 * @author SBI大連 夏
 * @date 2025/10/28
 */

@Component
public class IfaReleaseNoteFileUploadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteFileUploadUtil.class);

    public static Optional<Path> saveAndRenameReleaseNote(Path directory, String userId, String releaseNoteNo,
        MultipartFile mpFile) throws Exception {

        if (Objects.isNull(mpFile) || mpFile.isEmpty()) {
            return Optional.empty();
        }

        String dotExtension = Optional.ofNullable(FilenameUtils.getExtension(mpFile.getOriginalFilename()))
            .map(e -> (e.length() > 0 ? "." + e : e)).orElse(StringUtil.EMPTY_STRING);
        Path tempPath = generateTempFilePath(directory, userId, dotExtension);

        // 一時ファイル保存
        LOGGER.info("saving to:{}", tempPath.toString());
        mpFile.transferTo(tempPath.toFile());

        // リネーム処理
        // ※ファイル名採番ルール：[リリースノートNo6桁前0埋め]+"_"+[リクエスト.詳細ファイル]のファイル名＋["_1"からの連番]+"."+[リクエスト.詳細ファイル]の拡張子
        // 例：000001_資料_1.pdf
        int index = 0;
        String fileNameWithoutExt = Objects.requireNonNullElse(FilenameUtils.getBaseName(mpFile.getOriginalFilename()),
            StringUtil.EMPTY_STRING);
        Path realPath = null;
        do {
            // 初回
            if (index == 0) {
                if (!"".equals(fileNameWithoutExt) && !"".equals(dotExtension)) {
                    String realFileName =
                        String.format("%s_%s%s", padToSixDigits(releaseNoteNo), fileNameWithoutExt, dotExtension);
                    realPath = Paths.get(directory.toString(), realFileName);
                    LOGGER.info("renaming:{} to {}", tempPath.toString(), realPath.toString());
                } else if (!"".equals(fileNameWithoutExt) && "".equals(dotExtension)) {
                    String realFileName = String.format("%s_%s", padToSixDigits(releaseNoteNo), fileNameWithoutExt);
                    realPath = Paths.get(directory.toString(), realFileName);
                    LOGGER.info("renaming:{} to {}", tempPath.toString(), realPath.toString());
                } else if ("".equals(fileNameWithoutExt) && !"".equals(dotExtension)) {
                    String realFileName = String.format("%s%s", padToSixDigits(releaseNoteNo), dotExtension);
                    realPath = Paths.get(directory.toString(), realFileName);
                    LOGGER.info("renaming:{} to {}", tempPath.toString(), realPath.toString());
                }
                // 2回目以降（同名ファイルが存在する場合）
            } else {
                if (!"".equals(fileNameWithoutExt) && !"".equals(dotExtension)) {
                    String realFileName = String.format("%s_%s_%d%s", padToSixDigits(releaseNoteNo), fileNameWithoutExt,
                        index, dotExtension);
                    realPath = Paths.get(directory.toString(), realFileName);
                    LOGGER.info("renaming:{} to {}", tempPath.toString(), realPath.toString());
                } else if (!"".equals(fileNameWithoutExt) && "".equals(dotExtension)) {
                    String realFileName = String.format("%s_%s_%d", padToSixDigits(releaseNoteNo), fileNameWithoutExt, index);
                    realPath = Paths.get(directory.toString(), realFileName);
                    LOGGER.info("renaming:{} to {}", tempPath.toString(), realPath.toString());
                } else if ("".equals(fileNameWithoutExt) && !"".equals(dotExtension)) {
                    String realFileName = String.format("%s_%d%s", padToSixDigits(releaseNoteNo), index, dotExtension);
                    realPath = Paths.get(directory.toString(), realFileName);
                    LOGGER.info("renaming:{} to {}", tempPath.toString(), realPath.toString());
                }
            }
            ++index;
        } while (!moveFile(tempPath.toFile(), realPath.toFile()));
        return Optional.of(realPath);
    }

    /**
     * 一時ファイル名を生成して返却する
     * timestamp（yyyyMMdd_HHmmssSS）＋"_"＋ユーザID＋"."＋拡張子
     * 例：20221007_092844123_bits011.pdf
     */
    private static Path generateTempFilePath(Path dir, String userId, String dotExt) {

        var timeStamp = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS").format(LocalDateTime.now());
        var tempFileName = String.format("%s_%s%s", timeStamp, userId, dotExt);
        return Paths.get(dir.toString(), tempFileName);
    }

    /**
     * ファイルを移動する.(既存踏襲)
     *
     * @param srcFile  {@code String }
     * @param destFile {@code String }
     * @return 処理が完了した場合true,すでに存在する場合false
     * @throws Exception ファイル操作処理で例外が発生した場合
     */
    private static synchronized boolean moveFile(File srcFile, File destFile) throws Exception {

        try {
            // Linuxシステムでrenameメソッドが正しく動かないバグを修正するために、下記処理を追加する。
            // ファイルを移動する。
            FileUtils.moveFile(srcFile, destFile);
        } catch (FileExistsException e) {
            return false;
        } catch (Exception e) {
            LOGGER.error("Exception occured.", e.getMessage());
            throw e;
        }
        return true;
    }

    /**
     * 元のファイルを削除する
     *
     * @param filePath {@code String }
     * @param fileName {@code String }
     * @return 成功:True,失敗:False {@code Boolean}
     * @throws Exception ファイル操作処理で例外が発生した場合
     */
    public static Boolean deleteFromFileSyst(String filePath, String fileName) {
        try {
            File f = new File(filePath + fileName);
            // ファイルが存在すれば削除
            if (f.exists() && !f.isDirectory()) {
                if (f.delete()) {
                    return true;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Could not delete the file " + fileName + "from the server");
            throw e;
        }
        return false;
    }

    /**
     * リリースノートNo6桁前0埋め
     * 
     * @param input
     * @return
     */
    public static String padToSixDigits(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        return String.format("%6s", input).replace(' ', '0');
    }

}

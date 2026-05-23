package jp.co.sbisec.pcenter.dto.heracross;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

class HashBuilder {
	private static DateTimeFormatter fmt;
	private static String hashKey;
	private static LocalDate hashDate;
	private static String heraHash;

	static void init(String key) {
		fmt = DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT);
		hashKey = key;
	}

	static String get() {
		if (hashKey == null) {
			return "pcenter_heracross_not_initilized";
		}

		var date = LocalDate.now(ZoneId.of("Asia/Tokyo"));
		if (hashDate != null && date.isEqual(hashDate)) {
			return heraHash;
		}

		String x = date.format(fmt) + hashKey;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(x.getBytes());
			byte[] dgst = md.digest();

			StringBuilder sb = new StringBuilder(dgst.length * 2);
			for (byte b : dgst) {
				sb.append(String.format("%02x", b));
			}
			heraHash = sb.toString();
			hashDate = date;
		} catch (Exception e) {
			return "hash_convert_error";
		}

		return heraHash;
	}
}

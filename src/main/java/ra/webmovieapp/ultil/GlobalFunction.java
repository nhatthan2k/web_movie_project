package ra.webmovieapp.ultil;

import java.text.Normalizer;

public class GlobalFunction {
    public static String pathRevert(String value) {
        // Loại bỏ dấu thanh từ
        String withoutDiacritic = Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Chuyển các ký tự 'đ' và 'Đ' thành 'd' và 'D'
        String englishText = withoutDiacritic.replaceAll("[đĐ]", "d");

        // Chuyển các khoảng trắng thành dấu '-'
        String urlPath = englishText.replaceAll("\\s+", "-").toLowerCase();

        return urlPath;
    }
}


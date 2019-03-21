package com.cnlive.largeimage;

import android.text.TextUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileTypeUtil {
    public static final Map<String, String> FILE_TYPE_MAP = new HashMap();

    private FileTypeUtil() {
    }

    private static void getAllFileType() {
        FILE_TYPE_MAP.put("ffd8ff", "jpg");
        FILE_TYPE_MAP.put("89504e", "png");
        FILE_TYPE_MAP.put("474946", "gif");
        FILE_TYPE_MAP.put("49492a", "tif");
        FILE_TYPE_MAP.put("424d22", "bmp");
        FILE_TYPE_MAP.put("424d82", "bmp");
        FILE_TYPE_MAP.put("424d8e", "bmp");
        FILE_TYPE_MAP.put("414331", "dwg");
        FILE_TYPE_MAP.put("3c2144", "html");
        FILE_TYPE_MAP.put("3c2164", "htm");
        FILE_TYPE_MAP.put("48544d", "css");
        FILE_TYPE_MAP.put("696b2e", "js");
        FILE_TYPE_MAP.put("7b5c72", "rtf");
        FILE_TYPE_MAP.put("384250", "psd");
        FILE_TYPE_MAP.put("46726f", "eml");
        FILE_TYPE_MAP.put("d0cf11", "doc");
        FILE_TYPE_MAP.put("d0cf11", "vsd");
        FILE_TYPE_MAP.put("537461", "mdb");
        FILE_TYPE_MAP.put("252150", "ps");
        FILE_TYPE_MAP.put("255044", "pdf");
        FILE_TYPE_MAP.put("2e524d", "rmvb");
        FILE_TYPE_MAP.put("464c56", "flv");
        FILE_TYPE_MAP.put("000000", "mp4");
        FILE_TYPE_MAP.put("494433", "mp3");
        FILE_TYPE_MAP.put("000001", "mpg");
        FILE_TYPE_MAP.put("3026b2", "wmv");
        FILE_TYPE_MAP.put("524946", "wav");
        FILE_TYPE_MAP.put("524946", "avi");
        FILE_TYPE_MAP.put("4d5468", "mid");
        FILE_TYPE_MAP.put("504b03", "zip");
        FILE_TYPE_MAP.put("526172", "rar");
        FILE_TYPE_MAP.put("235468", "ini");
        FILE_TYPE_MAP.put("504b03", "jar");
        FILE_TYPE_MAP.put("4d5a90", "exe");
        FILE_TYPE_MAP.put("3c2540", "jsp");
        FILE_TYPE_MAP.put("4d616e", "mf");
        FILE_TYPE_MAP.put("3c3f78", "xml");
        FILE_TYPE_MAP.put("494e53", "sql");
        FILE_TYPE_MAP.put("706163", "java");
        FILE_TYPE_MAP.put("406563", "bat");
        FILE_TYPE_MAP.put("1f8b08", "gz");
        FILE_TYPE_MAP.put("6c6f67", "properties");
        FILE_TYPE_MAP.put("cafeba", "class");
        FILE_TYPE_MAP.put("495453", "chm");
        FILE_TYPE_MAP.put("040000", "mxp");
        FILE_TYPE_MAP.put("504b03", "docx");
        FILE_TYPE_MAP.put("d0cf11", "wps");
        FILE_TYPE_MAP.put("643130", "torrent");
        FILE_TYPE_MAP.put("3c6874", "htm");
        FILE_TYPE_MAP.put("46726f", "mht");
        FILE_TYPE_MAP.put("6D6F6", "mov");
        FILE_TYPE_MAP.put("FF575", "wpd");
        FILE_TYPE_MAP.put("CFAD1", "dbx");
        FILE_TYPE_MAP.put("21424", "pst");
        FILE_TYPE_MAP.put("AC9EB", "qdf");
        FILE_TYPE_MAP.put("E3828", "pwl");
        FILE_TYPE_MAP.put("2E726", "ram");
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src != null && src.length > 0) {
            for(int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static String getFileType(String filePath) {
        FileInputStream is = null;
        String value = null;

        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[3];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception var12) {
            ;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException var11) {
                    ;
                }
            }

        }

        String type = (String)FILE_TYPE_MAP.get(value);
        return type == null ? "" : type;
    }

    public static String getFileType(String filePath, boolean isImage) {
        FileInputStream is = null;
        String value = null;

        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[3];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception var13) {
            ;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException var12) {
                    ;
                }
            }

        }

        String type = (String)FILE_TYPE_MAP.get(value);
        if (isImage && !TextUtils.isEmpty(value) && "524946".equals(value)) {
            type = "webp";
        }

        return type == null ? "" : type;
    }

    public static String getFileType(InputStream stream) {
        String value = null;

        try {
            byte[] b = new byte[3];
            stream.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception var11) {
            ;
        } finally {
            if (null != stream) {
                try {
                    stream.close();
                } catch (IOException var10) {
                    ;
                }
            }

        }

        String type = (String)FILE_TYPE_MAP.get(value);
        return type == null ? "" : type;
    }

    static {
        getAllFileType();
    }
}

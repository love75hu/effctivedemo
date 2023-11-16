package cn.mediinfo.grus.shujuzx.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.zip.*;

public class GZIPUtils {
    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";

    public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";

    /**
     * 字符串压缩为GZIP字节数组
     *
     * @param str
     * @return
     */
    public static byte[] compress(String str) {
        return compress(str, GZIP_ENCODE_UTF_8);
    }

    /**
     * 字符串压缩为GZIP字节数组
     *
     * @param str
     * @param encoding
     * @return
     */
    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * GZIP解压缩
     *
     * @param bytes
     * @return
     */
    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    /**
     *
     * @param bytes
     * @return
     */
    public static String uncompressToString(byte[] bytes) {
        return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
    }

    /**
     *
     * @param bytes
     * @param encoding
     * @return
     */
    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /// <summary>
    /// 将传入字符串以GZip算法压缩后，返回Base64编码字符
    /// </summary>
    /// <param name="decompresseStr"></param>
    /// <param name="algorithm"></param>
    /// <returns></returns>
    public static String compressData(String decompressedStr) {
        try {
            // 将字符串转换为字节数组
            byte[] data = decompressedStr.getBytes("UTF-8");

            // 创建一个用于存储压缩数据的字节数组输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // 创建一个压缩输出流，使用GZip算法进行压缩
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(out, new Deflater(Deflater.DEFAULT_COMPRESSION, true));

            // 将数据写入压缩输出流
            deflaterOutputStream.write(data);

            // 关闭压缩输出流
            deflaterOutputStream.close();

            // 获取压缩后的字节数组
            byte[] compressedData = out.toByteArray();

            // 进行Base64编码
            String compressedStr = Base64.getEncoder().encodeToString(compressedData);

            return compressedStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /// <summary>
    /// 将传入的Base64字符串以GZip算法解压缩
    /// </summary>
    /// <param name="compressedData"></param>
    /// <param name="algorithm"></param>
    /// <returns></returns>
    public static String deCompressData(String compressedStr) {
        try {
            // 将Base64字符串解码为字节数组
            byte[] compressedData = Base64.getDecoder().decode(compressedStr);

            // 创建一个用于存储解压缩数据的字节数组输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // 创建一个解压缩输入流，使用GZip算法进行解压缩
            InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(compressedData), new Inflater(true));

            byte[] buffer = new byte[1024];
            int length;

            // 从解压缩输入流中读取数据并写入输出流
            while ((length = inflaterInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

            // 关闭解压缩输入流
            inflaterInputStream.close();

            // 获取解压缩后的字节数组
            byte[] decompressedData = out.toByteArray();

            // 将字节数组转换为字符串
            String decompressedStr = new String(decompressedData, "UTF-8");

            return decompressedStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

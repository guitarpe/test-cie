package br.com.clima.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@Slf4j
public class Utils {

    public static boolean verifyExistsFile(String file){
        Path path = Paths.get(file);

        return Files.exists(path);
    }

    public static void removeFile(String file) throws IOException {
        Path path = Paths.get(file);
        Files.delete(path);
    }

    public static boolean createFolder(String path) {
        try {
            if (!new File(path).exists()) {
                boolean dirCreated = new File(path).mkdir();
                permissionFolder(path);

                return dirCreated;
            }
            permissionFolder(path);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean copyDocument(String origin, String destiny) {
        File source = new File(origin);
        File dest = new File(destiny);

        try {
            FileUtils.copyFile(source, dest);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void permissionFolder(String pasta) throws IOException {
        File mainFolder = new File(pasta);

        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);

        Files.setPosixFilePermissions(mainFolder.toPath(), perms);
    }

    public static int genetareRandonInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String[] sliceStringArray(String[] arr, int tam) {
        String[] novoArray = new String[0];

        int i = 0;
        while (i < arr.length) {
            novoArray = Arrays.copyOfRange(arr, i, i + tam);
            i += tam;
        }

        return novoArray;
    }

    public static String convertToUtf8(String text) {
        byte[] utf8 = text.getBytes(StandardCharsets.UTF_8);

        return new String(utf8, StandardCharsets.UTF_8);
    }

    public static String convertToIso8859(String text) {
        byte[] utf8 = text.getBytes(StandardCharsets.ISO_8859_1);

        return new String(utf8, StandardCharsets.ISO_8859_1);
    }
}

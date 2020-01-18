package com.sl.util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

/**
 * @author：邵莲
 * @Date：2020/1/15 9:18
 *
 * 为Linux文件书写权限
 */
public class RightsUtil {
    public static void givePrevilege(String path) {
        if (path != null) {
            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            perms.add(PosixFilePermission.OWNER_EXECUTE);
            perms.add(PosixFilePermission.GROUP_READ);
            perms.add(PosixFilePermission.GROUP_EXECUTE);
            perms.add(PosixFilePermission.OTHERS_READ);
            perms.add(PosixFilePermission.OTHERS_EXECUTE);
            try {
                Path pathDest = Paths.get(path);
                Files.setPosixFilePermissions(pathDest, perms);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

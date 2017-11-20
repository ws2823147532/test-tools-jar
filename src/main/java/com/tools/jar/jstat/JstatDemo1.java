package com.tools.jar.jstat;

import com.tools.jar.jps.JpsCollections;
import com.tools.jar.jps.JpsUtil;

import java.util.Map;

/**
 * @author wangshang
 */
public class JstatDemo1 {

    public static void main(String[] args) {

        JpsCollections jpsCollections = JpsUtil.execute(new String[]{"-v", "-m", "-l", "-V"});
        Integer lvmid = jpsCollections.getJpsModels().stream().filter(model -> "com.tools.jar.RunDemo".equals(model.getMainClassname())).findFirst().get().getLvmid();
        System.out.println(lvmid);
        JstatCollections execute = JstatUtil1.execute(new String[]{JstatOption.GC.getOption(), "-t", String.valueOf(lvmid), "250", "20"});

        System.out.println(execute);
    }
}

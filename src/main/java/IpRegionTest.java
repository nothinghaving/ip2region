import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: mzd
 * @date: 2024年09月25日 14:51
 */

public class IpRegionTest {
    public static void main(String[] args) throws IOException {
        //创建离线数据库查询对象 searcher
        String dbPath="src/main/data/ip2region.xdb";
        Searcher searcher=null;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (IOException e) {
            System.out.printf("failed to create searcher with `%s`: %s\\n",dbPath,e);
            e.printStackTrace();
        }

        //查询数据
        String ip="58.222.20.35";
        try {
            long nanoTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - nanoTime);
            System.out.println("地址:"+region);
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\\n",region,searcher.getIOCount(),cost);
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n",ip,e);
        }
        searcher.close();

    }
}

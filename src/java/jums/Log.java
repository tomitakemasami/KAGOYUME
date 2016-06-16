
package jums;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.servlet.ServletException;


public class Log implements Serializable {

    public static Log getInstance() {
        return new Log();
    }
/**
 * 何か処理が発生するたびにその時点の時刻とともに引数に入れられたコメントが出力される
 * 
 */
    public void LogWrite(String words) throws ServletException, IOException {
        File tes = new File("C:\\Users\\kotaroh\\Documents\\NetBeansProjects\\kagoyume\\web\\WEB-INF\\log\\log.txt");
        Date date = new Date();
        BufferedWriter Rew = new BufferedWriter(new FileWriter(tes,true));
        Rew.write(words);
        Rew.write("実行時間" + date + "<br>");
        Rew.newLine();
        Rew.close();

    }
}

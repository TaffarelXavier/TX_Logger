/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Taffrel Xavier <taffarel_deus@hotmail.com>
 */
public class TXLogger {

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(TXLogger.class.getName());
    /**
     *
     */
    private static String pathFileName = "";

    /**
     *
     * @return
     */
    private static String getPathFileName() {
        return pathFileName;
    }

    /**
     *
     * @param pathFileName
     */
    public static void setPathFileName(String pathFileName) {
        TXLogger.pathFileName = pathFileName;
    }
    /**
     *
     */
    private static FileHandler fh = null;

    /**
     *
     * @param pathFileName
     */
    private static void iniciarLog() {
        try {
            fh = new FileHandler(getPathFileName(), true);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        fh.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fh);
        LOGGER.setLevel(Level.CONFIG);
    }

    /**
     *
     */
    private static void gravarLog() {
        try {
            File file;
            file = new File(getPathFileName());
            if (!file.exists()) {
                file.mkdir();
            }
            TXLogger.setPathFileName(getPathFileName() + "\\logSistema.log");
            TXLogger.iniciarLog();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    /**
     * <p style="font:16 arial black;"><br>Grava a mensagem no Log.</p>
     * <p>
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html">Logger:
     * acessar na web.</a></p>
     *
     * @param level Level, que pode ser Level+:
     * (ALL|FINE|FINER|CONFIG|INFO|SEVERE|WARNING)<br>
     * @param caminhoFileLog O caminho do <b>diretório</b>. O arquivo de log ser
     * criado automaticamente.<br>
     * @param msg A mensagem para gravar no arquivo de Log.
     */
    public static void executar(Level level, String caminhoFileLog, String msg) {

        TXLogger.setPathFileName(caminhoFileLog);

        TXLogger.gravarLog();

        LOGGER.log(level, msg.concat("<br/>------------------------------------------------------------"));
    }

}

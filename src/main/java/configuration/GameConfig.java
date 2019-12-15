package configuration;

/**
 * Map les valeurs du fichier config.properties en une classe Java.
 */
public class GameConfig {
    private Integer tryNum;
    private Boolean devMod;
    private Integer sizeCombi=0;



    /**
     * Construire l'objet GameConfig à partir du fichier porperties
     *
     * @return configuration souhaitée
     */
    public static GameConfig build(){
        GameConfig gameConfig = new GameConfig();

        gameConfig.tryNum = Integer.parseInt(SingletonConfigProperties.getPropertiesValues("tryNum"));
        gameConfig.devMod = Boolean.parseBoolean(SingletonConfigProperties.getPropertiesValues("devMod"));
        gameConfig.sizeCombi = Integer.parseInt(SingletonConfigProperties.getPropertiesValues("sizeCombi"));

        return gameConfig;
    }

    //Getteurs
    public Boolean getDevMod() {
        return devMod;
    }

    public Integer getTryNum() {
        return tryNum;
    }

    public Integer getSizeCombi() {
        return sizeCombi;
    }
}

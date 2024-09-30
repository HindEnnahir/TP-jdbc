
package scriptsdev;

public class DevData {

    private String developpeur;
    private String jour;
    private int nbscripts;

    public DevData() {
    }

    public DevData(String developpeur, String jour, int nbscripts) {
        this.developpeur = developpeur;
        this.jour = jour;
        this.nbscripts = nbscripts;
    }

    public String getDeveloppeur() {
        return developpeur;
    }

    public String getJour() {
        return jour;
    }

    public int getNbscripts() {
        return nbscripts;
    }

    public void setDeveloppeur(String developpeur) {
        this.developpeur = developpeur;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setNbscripts(int nbscripts) {
        this.nbscripts = nbscripts;
    }
    
}

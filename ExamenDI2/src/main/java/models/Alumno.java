package models;


public class Alumno {

    private Long ID;

    private String nombre;

    private String apellidos;

    private Integer AD;

    private Integer SGE;

    private Integer DI;

    private Integer PMDM;

    private Integer PSP;

    private Integer EIE;

    private Integer HLC;

    public Alumno(){

    }

    public Alumno(Long ID, String nombre, String apellidos, Integer AD, Integer SGE, Integer DI, Integer PMDM, Integer PSP, Integer EIE, Integer HLC) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.AD = AD;
        this.SGE = SGE;
        this.DI = DI;
        this.PMDM = PMDM;
        this.PSP = PSP;
        this.EIE = EIE;
        this.HLC = HLC;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getAD() {
        return AD;
    }

    public void setAD(Integer AD) {
        this.AD = AD;
    }

    public Integer getSGE() {
        return SGE;
    }

    public void setSGE(Integer SGE) {
        this.SGE = SGE;
    }

    public Integer getDI() {
        return DI;
    }

    public void setDI(Integer DI) {
        this.DI = DI;
    }

    public Integer getPMDM() {
        return PMDM;
    }

    public void setPMDM(Integer PMDM) {
        this.PMDM = PMDM;
    }

    public Integer getPSP() {
        return PSP;
    }

    public void setPSP(Integer PSP) {
        this.PSP = PSP;
    }

    public Integer getEIE() {
        return EIE;
    }

    public void setEIE(Integer EIE) {
        this.EIE = EIE;
    }

    public Integer getHLC() {
        return HLC;
    }

    public void setHLC(Integer HLC) {
        this.HLC = HLC;
    }
}

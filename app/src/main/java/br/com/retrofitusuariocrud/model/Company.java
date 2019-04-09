package br.com.retrofitusuariocrud.model;

public class Company {

    private String name;
    private String catchpharse;
    private String bs;

    public Company() {
    }

    public Company(String name, String catchpharse, String bs) {
        this.name = name;
        this.catchpharse = catchpharse;
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchpharse() {
        return catchpharse;
    }

    public void setCatchpharse(String catchpharse) {
        this.catchpharse = catchpharse;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}

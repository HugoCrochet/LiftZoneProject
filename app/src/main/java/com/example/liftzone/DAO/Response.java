package com.example.liftzone.DAO;

public class Response {

    private String m_nom ;
    private  int m_id;


    public Response (String na, int i){
        setM_nom(na);
        setM_id(i);

    }

    public String getM_nom() {
        return m_nom;
    }

    public void setM_nom(String m_nom) {
        this.m_nom = m_nom;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }



}
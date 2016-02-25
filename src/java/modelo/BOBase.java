/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class BOBase {

    private int totalDeRegistros;
    private int totalPaginas;
    private int totalRegistrosPorPaginas;
    private String ordem;
    private String sentido;
    
    public BOBase()
    {
        
    }
    
    public BOBase(int totalDeRegistros, int totalPaginas,int totalRegistrosPorPaginas, String ordem, String sentido)
    {
        setTotalDeRegistros(totalDeRegistros);
        setTotalPaginas(totalPaginas);
        setTotalRegistrosPorPaginas(totalRegistrosPorPaginas);
        setOrdem(ordem);
        setSentido(sentido);
        
        
        
    
    
    }

    public int getTotalDeRegistros() {
        return totalDeRegistros;
    }

    public void setTotalDeRegistros(int totalDeRegistros) {
        this.totalDeRegistros = totalDeRegistros;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public int getTotalRegistrosPorPaginas() {
        return totalRegistrosPorPaginas;
    }

    public void setTotalRegistrosPorPaginas(int totalRegistrosPorPaginas) {
        this.totalRegistrosPorPaginas = totalRegistrosPorPaginas;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }
    

    
    
   
    
    
    

}

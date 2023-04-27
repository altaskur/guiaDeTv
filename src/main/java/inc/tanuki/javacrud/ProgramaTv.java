package inc.tanuki.javacrud;

import java.util.Objects;

/**
 *
 * @author Altaskur
 *
 * CursosDeDesarrollo: implements Serializable
 * https://www.arquitecturajava.com/que-es-un-java-bean/#:~:text=Java%20Bean%20e%20Implementar%20Serializable,-Para%20que%20una&text=El%20interface%20Serializable%20es%20un,serializables%20a%20disco%20o%20a%20red.
 */
public class ProgramaTv {

    private Integer id;
    private String canal;
    private String titulo;
    private String horario;

    public ProgramaTv(Integer id, String canal, String titulo, String horario) {
        this.id = id;
        this.canal = canal;
        this.titulo = titulo;
        this.horario = horario;
    }

    public ProgramaTv() {
    }

    public Integer getId() {
        return id;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "ProgramaTv{" + "id=" + id + ", canal=" + canal + ", titulo=" + titulo + ", horario=" + horario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgramaTv other = (ProgramaTv) obj;
        return Objects.equals(this.id, other.id);
    }

}

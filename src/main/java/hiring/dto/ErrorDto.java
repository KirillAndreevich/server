package hiring.dto;

import java.io.Serializable;
import java.util.Objects;

public class ErrorDto implements Serializable{
    private String error;

    public ErrorDto() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDto errorDto = (ErrorDto) o;
        return Objects.equals(error, errorDto.error);
    }

    @Override
    public int hashCode() {

        return Objects.hash(error);
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "error='" + error + '\'' +
                '}';
    }
}

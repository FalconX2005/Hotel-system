package uz.pdp.hotelsystem.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Created by: Umar
 * DateTime: 2/10/2025 4:45 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Timestamp timestamp;
    private String error;
    private int status;

}

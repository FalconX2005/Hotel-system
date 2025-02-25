package uz.pdp.hotelsystem.payload;

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

    private Timestamp timestamp;
    private String error;
    private int status;

}

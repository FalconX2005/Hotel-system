package uz.pdp.hotelsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE password_reset_token SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class PasswordResetToken extends AbsLongEntity {

    private String token;

    @OneToOne
    private User user;


    private Date expirationDate;

    public PasswordResetToken(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        this.expirationDate = new Date(System.currentTimeMillis() + 60 * 60 * 1000); // 1 soat
    }

    public boolean isExpired() {
        return new Date().after(this.expirationDate);
    }

}

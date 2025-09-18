/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TinTuc {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATT")
    @NotNull(message = "Mã tin tức không được để trống")
    private Long maTinTuc;
    @Column(name = "TIEUDE")
    @NotBlank(message = "Tiêu đề không được để trống")
    private String tieuDe;
    @Column(name = "NOIDUNGTT")
    @NotBlank(message = "Nội dung không được để trống")
    @Size(max = 255, message = "Nội dung không được quá 255 ký tự")
    private String noiDungTinTuc;
    @Column(name = "LIENKET")
    @Pattern(regexp = "^http://.*", message = "Liên kết không được để trống và phải bắt đầu bằng http://")
    private String lienKet;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "MADM")
    private DanhMuc danhMuc;


}

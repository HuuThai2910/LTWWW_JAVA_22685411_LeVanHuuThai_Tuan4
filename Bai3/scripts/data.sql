INSERT INTO DANHMUC (MADM, TENDANHMUC, NGUOIQUANLY, GHICHU) VALUES
                                                                (1, N'Công nghệ', N'Nguyễn Văn A', N'Tin tức về công nghệ mới'),
                                                                (2, N'Giáo dục', N'Trần Thị B', N'Thông tin giáo dục, đào tạo'),
                                                                (3, N'Sức khỏe', N'Lê Văn C', N'Cập nhật kiến thức y tế'),
                                                                (4, N'Kinh tế', N'Phạm Thị D', N'Bản tin tài chính, chứng khoán'),
                                                                (5, N'Thể thao', N'Hoàng Văn E', N'Tin tức bóng đá, thể thao khác');

INSERT INTO TINTUC (MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES
                                                                (101, N'Ra mắt iPhone mới', N'Apple chính thức giới thiệu iPhone 17 Pro Max...', N'https://apple.com/iphone17', 1),
                                                                (102, N'Ứng dụng AI trong học tập', N'Các trường học bắt đầu áp dụng AI...', N'https://edu.vn/ai-learning', 1),
                                                                (103, N'Phòng chống dịch bệnh mùa mưa', N'Bộ Y tế khuyến cáo người dân...', N'https://moh.gov.vn/health', 2),
                                                                (104, N'Thị trường chứng khoán tăng mạnh', N'VN-Index vượt 1.300 điểm...', N'https://vnexpress.net/finance', 1),
                                                                (105, N'Đội tuyển Việt Nam thắng 3-0', N'Tuyển Việt Nam giành chiến thắng...', N'https://bongda.vn/vietnam-3-0', 2);

SELECT*FROM TINTUC WHERE MADM=1
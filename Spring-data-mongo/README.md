# :leaves:  Spring Data - mongodb :leaves:

### Yêu cầu
- Biết về spring core  :thumbsup:
- Biết về restfull api :thumbsup:
- Biết về spring boot :thumbsup:
- Biết về lombok :thumbsup:

## Spring Data 

- Hỗ trợ kết nối database như là mongodb,sqlserver,mysql,...

- Giảm thiểu được số lượng code đáng kể 

- Như Các chức năng như thêm,xóa,sửa,...tất cả đã được viết sẵn nên hạn chế được boilderplate (code lặp lại)

- Để sử dụng các chức năng CRUD, mình chỉ cần extends các abstraction của Repository

## Maven
```xml
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

### Có 4 package chính 
1. models
2. repositorys
3. services
4. controllers

### Entity model

- Là các đối tượng

- Khi muốn ánh xạ các đối tượng này xuống dữ liệu ta phải khai báo - annotation : @Document

- Tạo tự động getter,setter,constructor thông qua lombok @Data

    
        @Data
        @Document
        public class DienVien {
        	@Id
        	private ObjectId idDV;
        	
        	private String tenDV;
        }

### Repository 

- Nó đảm nhận các đối tượng, ID

- Như trong mongodb nó đã có sẵn chức năng CRUD ta chỉ cần extends Repository

Cấu trúc chung cho các đối tượng 

    public interface CrudRepository<T, ID> extends Repository<T, ID> {
      
     // tìm id
      Optional<T> findById(ID primaryKey); 
    
      //liệt kê danh sách
      Iterable<T> findAll();               
    
      //số lượng 
      long count();                        
    
     // xóa đối tượng
      void delete(T entity);               
    
      //kiểm tra sự tồn tại id
      boolean existsById(ID primaryKey);   
    
       //… more functionality omitted.
    }

==>ngoài ra còn có JpaRepository, MongoRepository nó extends từ CrudRepository

Như trong class DienVien ta có thuộc tính id chính là kiểu Long 

Thì ta sẽ viết thế này 
```java
interface DienVienRepository extends Repository<DienVien, Long> { … }
```

Còn nếu là kiểu String 
```java
interface DienVienRepository extends Repository<DienVien, String> { … }
```

Trong DienVienRepository có ObjectId là id chính nên ta sẽ có :

    @Repository
    public interface DienVienRepository extends MongoRepository<DienVien, ObjectId>{
    	DienVien save(DienVien entity);
    
    	//tìm id
    	Optional<DienVien> findByIdDV(ObjectId id);
    
    	//liệt kê danh sách
    	List<DienVien> findAll();               
    
    	//số lượng 
    	long count();                        
    
    	//xóa đối tượng
    	void delete(DienVien entity);               
    
    	//kiểm tra sự tồn tại id
    	boolean existsById(ObjectId idDV); 
    }
    
### Service

Sẽ có DienVienService 
- Controller sẽ gọi thông qua interface này

DienVienServiceImpl 
- Sẽ implement interface DienVienService 
- Và tiêm DienVienRepository để thực hiện các chức năng có sẵn
 
### Controller

Sẽ đảm nhận thực hiện các request yêu cầu bên phía client

- Còn local sẽ chạy mặc định trên 8080

- Bạn có thể sử dụng postman để gọi các api này

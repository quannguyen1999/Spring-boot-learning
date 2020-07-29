# :leaves:  Spring Data - mongodb :leaves:

### Yêu cầu :tw-270b:
- Biết về spring core  :thumbsup:
- Biết về restfull api :thumbsup:
- Biết về spring boot :thumbsup:
- Biết về lombok :thumbsup:

## Spring Data 

- Hỗ trợ kết nối database như là mongodb,sqlserver,mysql,...

- Giảm thiểu được số lượng code đáng kể (boilderplate code) như thêm,xóa,sửa,...(tất cả đã được viết sẵn)

- Để sử dụng các chức năng CRUD, mình chỉ cần extends các abstraction của Repository trong spring data là xong :)

### Entity model

- Là các đối tượng

vd: Đối tượng DienVien có thuộc tính tenDV

- Khi muốn ánh xạ các đối tượng này xuống dữ liệu ta phải khai báo - annotation:@Document

- Tạo tự động getter,setter,constructor thông qua lombok @Data

    
        @Data
        @Document
        public class DienVien {
        	@Id
        	private ObjectId idDV;
        	
        	private String tenDV;
        }

### Repository 

- Nó đảm nhận các đối tượng cũng như là ID và thuộc tính của đối tượng đó

- Như CrudReposity dành cho mongodb nó đã có sẵn chức năng CRUD ta chỉ cần extends class đó thôi 

vd 

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

vd như trong class Person ta có thuộc tính id chính là kiểu Long 

thì ta sẽ viết thế này 
interface PersonRepository extends Repository<Person, Long> { … }

còn nếu là kiểu String 
interface PersonRepository extends Repository<Person, String> { … }

ở trong DienVienRepository sẽ có 

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
- controller sẽ gọi thông qua interface này

DienVienServiceImpl 
- Sẽ implement interface DienVienService 
- Và tiêm DienVienRepository để thực hiện các chức năng có sẵn
 
### Controller


=> ok :mag_right: Đó là những chức năng cơ bản mà mình đã liệt kê ở trên 
=>Mình sẽ chia package ra làm 4 loại như giải thích ở trên

2. models
3. repositorys
4. services
5. controllers

- Còn local sẽ chạy mặc định trên 8080

- Bạn có thể sử dụng postman để gọi các api này
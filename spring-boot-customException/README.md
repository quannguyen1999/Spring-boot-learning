# Error handling :leaves:
## Basic
### Yêu cầu 
- Biết về spring core  :thumbsup:
- Biết về spring data mongodb :thumbsup:
- Biết về restfull api :thumbsup:
- Biết về spring boot :thumbsup:
- Biết về lombok :thumbsup:

## Error Handling

Trả về lỗi Json theo ý muốn của mình 

Ở đây có 5 package chính 

Mình chỉ nói sơ lược các package đã học, còn package exceptions sẽ giải thích kỹ hơn

### Entity models

- Là các đối tượng

- Khi muốn ánh xạ các đối tượng này xuống dữ liệu ta phải khai báo - annotation: @Document

- Tạo tự động getter,setter,constructor thông qua lombok @Data

    
        @Data
        @Document
        public class DienVien {
        	@Id
        	private ObjectId idDV;
        	
        	private String tenDV;
        }

### Repositorys 

- Thực hiện các Query 

- Các chức năng có sẵn CRUD
    
### Services

Sẽ có DienVienService 
- Controller sẽ gọi thông qua interface này

DienVienServiceImpl 
- Thực hiện các chức năng và extends từ DienVienService
 
### Controllers
Nơi xử lý các yêu cầu từ phía client

### Exceptions
Nơi xử lý và trả về các lỗi theo mong muốn của mình

#### Ở đây mình khai báo 3 class :

#### Class ErrorResponse 

+Bao gồm các thuộc tính là message, và details

```java
@XmlRootElement(name = "error")
public class ErrorResponse {
	//tên lỗi
    private String message;
 
    //lỗi cụ thể (như id)
    private List<String> details;
}
```

#### Class NotFoundException
+Là class mình tự định nghĩa khi có lỗi gì, nó sẽ quăng vào đây
+Bạn muốn đặt tên class gì cũng được miễn là phải có @ResponseStatus(HttpStatus.(kiểu bạn định nghĩa))

```java

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

	public  NotFoundException(String exception) {
		super(exception);
	}
}
```


#### CustomExceptionHandler 
+Là nơi trả về Json của bạn và status 

+Có 3 kiểu 

+1 là lỗi server 

+2 là lỗi trong class NotFoundException

+3 là lỗi trong validator model


    @SuppressWarnings({"unchecked","rawtypes"})
    @ControllerAdvice(basePackageClasses = {DienVienController.class})
    public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
    	
    	@ExceptionHandler(Exception.class)
    	@ResponseBody
    	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    		
    		List<String> details = new ArrayList<>();
    		
    		details.add(ex.getLocalizedMessage());
    		
    		ErrorResponse error = new ErrorResponse("Server Error", details);
    		
    		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    
    	@ExceptionHandler(NotFoundException.class)
    	@ResponseBody
    	public final ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
    		List<String> details = new ArrayList<>();
    		
    		details.add(ex.getLocalizedMessage());
    		
    		ErrorResponse error = new ErrorResponse("Record Not Found", details);
    		
    		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    		
    	}
    
    
    	@Override
    	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    		List<String> details = new ArrayList<>();
    		
    		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
    			
    			details.add(error.getDefaultMessage());
    			
    		}
    		
    		ErrorResponse error = new ErrorResponse("Validation Failed", details);
    		
    		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    	}
    }
- Còn local sẽ chạy mặc định trên 8080

- Sau đó bạn dùng postman để test chương trình nó sẽ hiễn thị các lỗi như là 

ví dụ 
```json
{
    "message": "Validation Failed",
    "details": [
        "nickName không được rỗng"
    ]
}
```

hoặc 

HTTP Status : 404

```json
{
    "message": "Record Not Found",
    "details": [
        "Invalid employee id : 23"
    ]
}
```

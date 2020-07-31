# :leaves:  Spring hateoas :leaves:

### Yêu cầu
- Biết về spring core  :thumbsup:
- Biết về restfull api :thumbsup:
- Biết về spring boot :thumbsup:
- Biết về lombok :thumbsup:
- Biết về spring data :thumbsup:
- Biết về customerException :thumbsup:

## Maven
<!-- spring hateous -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
		</dependency>

## Spring hateoas
+Tạo model với cùng với các link đính kèm như thêm, xóa, tìm kiếm 
+Client có thể tự động điều hướng đến tài nguyên phù hợp bằng cách
 duyệt qua các liên kết hypermedia
+Nó tương tự như một người dùng web điều hướng qua các trang web bằng
cách nhấp vào các link thích hợp để chuyển đến nội dung mong muốn

VÍ dự khi bạn truy cập một file student

Ngoài Student profile nó sẽ các thông tin đính kèm như:
- Đường link sửa profile, chi tiết thông tin profiles
- Các chi tiết thông tin khóa học, điểm,...

## Cách sử dụng
##### B1:Ở các model bạn extends RepresentationModel<T> để add các link vào

ví dụ như ở customer
```java
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.RepresentationModel;
@Document
public class Customer extends RepresentationModel<Customer>{
	
	@Id
	private ObjectId customerId;
	
    private String customerName;
    
    private String companyName;
}

```
##### B2:Ở controller 
Vd như ở 
```java
Link link=linkTo(methodOn(this.getClass()).chiTietKhachHang(idCUstomer)).withRel("chi tiết khách hàng")
```

- LinkTo: kiểm tra class controller và lấy đường dẫn
- Slash: gắn id vào sau linkTo
- Method On: fake phương thức trong class này

* lưu ý :boom:
Phải khai báo những thư viện này mới xài được

```java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

```

	@GetMapping(value = "/orders/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Order>> danhSachOrderQuaId(@PathVariable("id") String id){
		
		return new ResponseEntity<List<Order>>(orderService.danhSachOrderByCustomerId(new ObjectId(id)),HttpStatus.OK);

	}

	

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> danhSachCustomer(HttpServletRequest request){

		List<Customer> listCustomerFromDB=customerService.danhSachCustomer();
		
		for(int i=0;i<listCustomerFromDB.size();i++) {
			
			String idCUstomer=listCustomerFromDB.get(i).getCustomerId().toString();
			
			listCustomerFromDB.get(i).
				add(
					linkTo(methodOn(this.getClass()).chiTietKhachHang(idCUstomer)).withRel("chi tiết khách hàng")
				);
			
			List<Order> listOrder=orderService.danhSachOrderByCustomerId(listCustomerFromDB.get(i).getCustomerId());
			
			if(listOrder.size()>0) {

				listCustomerFromDB.get(i).add(linkTo(methodOn(CustomerController.class).danhSachOrderQuaId(idCUstomer)).withRel("danh sách order"));

			}

		}

		return listCustomerFromDB;

	}

##### B3:Sau đó bạn truy cập http://localhost:8080/api/customers

sẽ có các thông tin
```json
[
    {
        "customerId": {
            "timestamp": 1596117739,
            "date": "2020-07-30T14:02:19.000+00:00"
        },
        "customerName": "quan",
        "companyName": "googleHome",
        "links": [
            {
                "rel": "chi tiết khách hàng",
                "href": "http://localhost:8080/api/customers/5f22d2ebd88cc1255e01babf"
            },
            {
                "rel": "danh sách order",
                "href": "http://localhost:8080/api/customers/orders/5f22d2ebd88cc1255e01babf"
            }
        ]
    }
 ]
```


### :smiling_imp: Advanced :smiling_imp:
Xử lý phần view cho client
- Như ở server bạn có thể truy cập để xem các lỗi thông tin trong json 
- Nhưng ở client bạn cần phải hiển thị view giao diện các lỗi cơ bản như 404, 500

Lưu ý 

Bạn cần phải xem phần basic customException
link: https://github.com/quannguyen1999/Spring-boot-learning/tree/master/spring-boot-customException

###### B1 
khai báo maven (này để sử dụng các tag th)

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		
###### B2 
sau đó ở resources/templates 
tạo file error.html

Ở resources/static 
tạo 2 folder js (error.js), css(error.css)

###### B3 
Mình thêm 1 exception mới là BadRequestException trả về BadRequest (400)

###### B4 
- Tạo một class CustomErrorController 
- Khi có lỗi status loại nào thì nó sẽ set thuộc tính trong errorPage và trả qua cho người dùng qua error.html
- Lưu ý là khi bạn dùng postman thì nó vẫn trả file Json 
- Còn khi truy cập qua các trang web như chrome, cốc cốc,... thì trả về view giao diện cho người dùng

```java
Controller
@RequestMapping
public class CustomErrorController implements ErrorController{

	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
			request.setAttribute("errorPage","400");
		}
		else if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			request.setAttribute("errorPage","500");
		}
		else {
			request.setAttribute("errorPage","404");
		}
		
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
```




		
		

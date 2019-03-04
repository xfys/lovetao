# LoveTao
爱淘

包名
com.inner.lovetao
MD5应用签名
d85d28ecb19c338338c9d06c755c7aa7


# 切图地址
https://modao.cc/app/v2SSCdXkdXtLs1YP2weZKZDvqwLzN2c

#颜色命名
color_色值
#drawable命名



#drager2 使用
#不带参数
编写步骤：
第一，将我们需要注入的对象的类的构造参数使用@Inject标注，告诉dagger2它可以实例化这个类；
第二，编写Component接口使用@Component进行标注，里面的void inject()的参数表示要将依赖注入到的目标位置；
第三，使用android studio的Build菜单编译一下项目，使它自动生成我们编写的Component所对应的类，生成的类的名字的格式为 "Dagger+我们所定义的Component的名字"；
第四，在需要注入的类中使用@Inject标注要注入的变量；然后调用自动生成的Component类的方法create()或builder().build()，然后inject到当前类；在这之后就可以使用这个@Inject标注的变量了。
#Log打印使用 Arms里面Logutils

#所有Activity使用Arouter进行跳转 

  # 1.Activity标记Arouter path 在ArouterConfig里面进行存档 
  
  # 2.若该Activity不需要登录则在LoginInterceptor 中加入该路径，默认需要登录
  
  # 3. 不带参数跳转：   ARouter.getInstance().build(路径).navigation(Context（可不传，默认为application context）);
  
  # 4. 带参数跳转：  ARouter.getInstance().build(路径).withInt(key,value).withSerializable(key,value).withBundle(key,value).navigation(this);
  
  # 5. 带参数取值    在跳转过去的页面 注解  @Autowired(name = key)    int intNumber;  
# 淘宝登陆SDK     http://baichuan.taobao.com/docs/doc.htm?spm=a219a.7386653.0.0.6ab6669acgkiTg&docType=1&articleId=107136&previewCode=670FC3B0EF08CC498F6943B01519C2A7
#前台管理接口地址   http://47.105.249.19:8089/swagger-ui.html
                                               
                                     

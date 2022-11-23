package pers.qc233.basemanagesystem.Config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {

        @Bean
        public MybatisPlusInterceptor mPInterceptor(){
            MybatisPlusInterceptor mPInterceptor = new MybatisPlusInterceptor();
            mPInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
            return mPInterceptor;
        }

}

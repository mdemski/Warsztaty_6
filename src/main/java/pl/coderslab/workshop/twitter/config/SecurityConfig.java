package pl.coderslab.workshop.twitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/twitter?serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("coderslab");
        return dataSource;
    }

    @Override //proces autentykacji użytkownika
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication() //konieczne do konfiguracji
                .passwordEncoder(passwordEncoder()) //narzędzie do odczytu zakodowanego hasła
                .dataSource(dataSource()) //metoda zwraca konfigurację do zedifniowanego źródła danych - w tym wypadku bazy danych
                .usersByUsernameQuery("SELECT email, password, true FROM users WHERE email = ?") //zapytania natywne czy użytkownik jest aktywny
                .authoritiesByUsernameQuery("SELECT email, 'ROLE_USER' FROM users WHERE email = ?"); //zapytanie czy użytkownik ma dostęp do ról
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                //and wracamy do konfiguracji ogólnej
                .csrf().disable()
                //wyłączamy analizę csrf
                .authorizeRequests()
                //autoryzacja dostępu w tym wypadku dla wszystkich
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                // wyciągamy role z bazy jeśli chcemy ograniczyć dostęp względem ról (role są najczęściej osobną tabelą ROLE_ADMIN nazwa z bazy danych
                .anyRequest().authenticated();
        //robić na końcu bo to idzie od góry (powyższe wykluczają to wymuszenie autoryzacji).
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }


}

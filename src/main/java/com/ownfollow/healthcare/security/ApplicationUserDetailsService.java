package com.ownfollow.healthcare.security;

import com.ownfollow.healthcare.entity.Account;
import com.ownfollow.healthcare.exception.ErrorConstants;
import com.ownfollow.healthcare.repository.AccountRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.function.Supplier;


@Service
public class ApplicationUserDetailsService implements UserDetailsService {

  private final AccountRepository accountRepository;

  public ApplicationUserDetailsService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public UserDetails loadUserByUsername(final String login) {
    Supplier<UsernameNotFoundException> ex = () -> new UsernameNotFoundException(
            String.format(ErrorConstants.USER_NOT_FOUND_ERR, login));
    if (new EmailValidator().isValid(login, null)) {
      return accountRepository.findByEmail(login)
              .map(this::createUser)
              .orElseThrow(ex);
    }
    String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
    return accountRepository.findByUsername(lowercaseLogin)
            .map(this::createUser)
            .orElseThrow(ex);
  }

  private User createUser(Account account) {
    return new User(account.getUsername(),
            account.getPassword(), account.getAuthorities());
  }
}

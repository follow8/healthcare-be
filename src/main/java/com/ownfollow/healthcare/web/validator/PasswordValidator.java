package com.ownfollow.healthcare.web.validator;

import com.ownfollow.healthcare.annotation.ValidPassword;
import lombok.NoArgsConstructor;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

  public static final int MIN_PASSWORD_LENGTH = 4;
  public static final int MAX_PASSWORD_LENGTH = 16;

  @Override
  public void initialize(ValidPassword constraintAnnotation) {
    // default initialization
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    org.passay.PasswordValidator validator = new org.passay.PasswordValidator(
        Arrays.asList(
            new LengthRule(MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1),
            new WhitespaceRule()
        )
    );
    RuleResult result = validator.validate(new PasswordData(value));
    if (result.isValid()) {
      return true;
    }
    List<String> messages = validator.getMessages(result);
    String msgTemplate = String.join(",", messages);
    context.buildConstraintViolationWithTemplate(msgTemplate)
        .addConstraintViolation()
        .disableDefaultConstraintViolation();
    return false;
  }
}

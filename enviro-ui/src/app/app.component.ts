import {Component, OnInit, ViewChild} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {UserTo} from './model/user-to';
import {AccessControlService} from './service/access-control.service';
import {Utils} from './common/utils';
import {MatSnackBar} from '@angular/material';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  registeredUser: UserTo;
  registrationFormGroup: FormGroup;
  @ViewChild('optionGroup') optionGroup;
  isLoading = false;

  constructor(private formBuilder: FormBuilder,
              private snackBar: MatSnackBar,
              private accessControlService: AccessControlService) {
  }

  ngOnInit(): void {
    this.optionGroup.value = 'age';
    this.registrationFormGroup = this.formBuilder.group({
      email: [null, [Validators.required, Validators.pattern('.{1,}@[a-zA-Z0-9.-]{2,}[.]{1}[a-zA-Z]{2,}')]],
      fullName: [null, Validators.required],
      age: [null, Validators.required],
      idNumber: [null, Validators.compose([Validators.required, this.isValidIdNumber()])],
      gender: [null, Validators.required],
    });
  }

  public onIdNumberChange(idNumber: number): void {
    if (Utils.isValidSAIdNumber(idNumber.toString())) {
      this.registrationFormGroup.get('gender')
        .setValue(Utils.getGenderFromIdNumber(idNumber.toString()));
      this.registrationFormGroup.get('age')
        .setValue(Utils.calculateAgeFromDateOfBirth(
          Utils.getDateOfBirthFromIdNumber(idNumber.toString())));
    }
  }

  private isValidIdNumber(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (this.registrationFormGroup && control.value) {
        const idNumber = control.value;
        if (!Utils.isValidSAIdNumber(idNumber.toString())) {
          return {idNumber: {valid: false}};
        }
      }
      return null;
    };
  }

  public registerUser(): void {
    this.isLoading = true;
    if (this.optionGroup.value === 'age') {
      this.registrationFormGroup.get('idNumber').setErrors(null);
    }

    if (this.registrationFormGroup.valid) {
      const user = new UserTo();
      user.fullName = this.registrationFormGroup.get('fullName').value;
      user.email = this.registrationFormGroup.get('email').value;
      user.age = this.registrationFormGroup.get('age').value;
      user.idNumber = this.registrationFormGroup.get('idNumber').value as string;
      user.gender = this.registrationFormGroup.get('gender').value;

      this.accessControlService.registerUser(user).subscribe((data) => {
        this.registeredUser = data;
        this.isLoading = false;
        this.snackBar.open('Successfully registered...!!!', 'CLOSE', {
          duration: 4000,
        });
      }, (error: HttpErrorResponse) => {
        this.isLoading = false;
        this.snackBar.open(error.error.message, 'CLOSE', {
          duration: 4000,
        });
      });
    } else {
      this.validateAllFormFields(this.registrationFormGroup);
    }
  }

  private validateAllFormFields(formGroup: FormGroup): void {
    Object.keys(formGroup.controls).forEach((field) => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({onlySelf: true});
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }
}

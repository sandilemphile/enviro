import * as moment from 'moment';
import {now} from 'moment';

export class Utils {

  public static getGenderFromIdNumber(idNumber): string {
    if (this.isValidSAIdNumber(idNumber)) {
      const genderValue = idNumber.substring(6, 10);
      return genderValue >= 5000 ? 'MALE' : 'FEMALE';
    }
    return null;
  }


  public static isValidSAIdNumber(idNumber: string): boolean {
    if (/[^0-9-\s]+/.test(idNumber)) {
      return false;
    }

    idNumber = idNumber.replace(/\D/g, '');

    let oddSum = 0;
    let evenSequence = '';

    for (let i = 0; i < idNumber.length - 1; i += 2) {
      oddSum += parseInt(idNumber.charAt(i), 10);
    }
    for (let i = 1; i < idNumber.length; i += 2) {
      evenSequence += idNumber.charAt(i);
    }

    evenSequence = '' + (parseInt(evenSequence, 10) * 2);
    let totalEvenSequence = 0;

    for (let i = 0; i < evenSequence.length; i++) {
      totalEvenSequence += parseInt(('' + evenSequence).charAt(i), 10);
    }

    const total = oddSum + totalEvenSequence;
    let check = 10 - parseInt(('' + total).charAt(1), 10);

    if (check > 9) {
      check = parseInt(('' + check).charAt(1), 10);
    }
    return parseInt(idNumber.charAt(12), 10) === check;
  }

  public static calculateAgeFromDateOfBirth(dateOfBirth: string): number {
    const currentDate = moment();
    const dob = moment(dateOfBirth, 'DD-MM-YYYY');
    return dob.isValid() ? currentDate.diff(dob, 'years') : null;
  }

  public static getDateOfBirthFromIdNumber(idNumber: string): string {
    if (this.isValidSAIdNumber(idNumber)) {
      const day = idNumber.substring(4, 6);
      const month = idNumber.substring(2, 4);
      const year = idNumber.substring(0, 2);
      const currentDate = moment();
      const refDate = moment('01-01-2000', 'DD-MM-YYYY');
      if (parseInt(year, 10) <= currentDate.diff(refDate, 'years')) {
        return `${day}-${month}-20${year}`;
      } else {
        return `${day}-${month}-19${year}`;
      }
    }
    return null;
  }
}

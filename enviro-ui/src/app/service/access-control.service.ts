import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserTo} from '../model/user-to';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccessControlService {

  constructor(private httpClient: HttpClient) {
  }

  public registerUser(user: UserTo): Observable<UserTo> {
    return this.httpClient.post<UserTo>('/api/v1/access-control/sign-up', JSON.stringify(user),
      {headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }
}

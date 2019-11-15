import { Injectable, OnInit, AfterContentChecked } from '@angular/core';
import { AccountService } from 'app/core/auth/account.service';
import { AppUserService } from 'app/entities/app-user/app-user.service';
import { Account } from 'app/core/user/account.model';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { AppUser } from 'app/shared/model/app-user.model';
import { Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class GlobalVariablesService implements OnInit, AfterContentChecked {
  title = '';
  rehabCenterId = new Subject<any>();
  rehabCenter;

  isLoading = false;
  isInForm = false;
  isSaving;

  account: Account;
  userApp = new Subject<any>();

  constructor(private accountService: AccountService, private appUserService: AppUserService) {
    this.accountService.getAuthenticationState().subscribe(data => {
      if (data) {
        this.defineGlobalRehabCenter();
      }
    });
  }

  ngOnInit(): void {}

  ngAfterContentChecked() {
    // this.cdRef.detectChanges();
  }

  public setTitle(title) {
    this.title = title;
  }

  public loading() {
    this.isLoading = true;
  }

  public loaded() {
    this.isLoading = false;
  }

  public enteringForm() {
    this.isInForm = true;
  }

  public setFormStatus(isSaving) {
    this.isSaving = isSaving;
  }

  public leavingForm() {
    this.isInForm = false;
  }

  public defineGlobalRehabCenter() {
    this.appUserService
      .findByCurrentUser()
      .pipe(
        filter((response: HttpResponse<AppUser>) => response.ok),
        map((appUser: HttpResponse<AppUser>) => appUser.body)
      )
      .subscribe(userApp => {
        this.userApp.next(userApp);
        const rehabCenterId = userApp.rehabilitationCenterId;
        this.rehabCenterId.next(rehabCenterId);
        this.rehabCenter = rehabCenterId;
      });
  }
}

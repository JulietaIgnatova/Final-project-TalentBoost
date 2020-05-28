import { Component, OnInit } from '@angular/core';
import {CharityService } from '../../services/charity.service';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-create-charity',
  templateUrl: './create-charity.component.html',
  styleUrls: ['./create-charity.component.css']
})
export class CreateCharityComponent implements OnInit {
  charityForm: FormGroup;
  validMessage: string = "";
  selectedFile: File;

  constructor(private charityService: CharityService) { }

  ngOnInit(): void {
    this.charityForm = new FormGroup({
      creatorId: new FormControl('',Validators.required),
      title: new FormControl('',Validators.required),
      description: new FormControl('',Validators.required),
      budgetRequired: new FormControl('',Validators.required),
      volunteersRequired: new FormControl('',Validators.required)
    })
  }

  submitCharity() {
    if(this.charityForm.valid){
      this.validMessage = "Your charity form has been submitted. Thank you!"
      const submitFormData = new FormData;
      submitFormData.append('imageFile', this.selectedFile);
      let body = JSON.stringify(this.charityForm.value);
      submitFormData.append('body',body);
    
      this.charityService.createCharityWithImage(submitFormData).subscribe(
        date => {
          this.charityForm.reset();
          this.selectedFile = null;
          return true;
        },
        error => {
          return Observable.throw(error)
        }
      )
    } else {
      this.validMessage = "Please fill out the form before submitting!"
    }
  }

  processFile(imageInput: any){
    this.selectedFile = imageInput.target.files[0];
  }

}
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemQuotComponent } from './item-quot.component';

describe('ItemQuotComponent', () => {
  let component: ItemQuotComponent;
  let fixture: ComponentFixture<ItemQuotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemQuotComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ItemQuotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

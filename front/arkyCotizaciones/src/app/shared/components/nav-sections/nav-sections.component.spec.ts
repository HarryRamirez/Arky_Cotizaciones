import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavSectionsComponent } from './nav-sections.component';

describe('NavSectionsComponent', () => {
  let component: NavSectionsComponent;
  let fixture: ComponentFixture<NavSectionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavSectionsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NavSectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

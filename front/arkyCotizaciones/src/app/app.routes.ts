import { Routes } from '@angular/router';

export const routes: Routes = [

    {
        path: 'dashboard',
        loadComponent: () => import('./dashboard/dashboard.component'),
        children: [


            {
                path: 'home',
                title: 'Home',
                loadComponent: () => import('./dashboard/pages/home/home.component')
            },
        
            {
                path: 'client',
                title: 'Client',
                loadComponent: () => import('./dashboard/pages/customer/client.component')
            },

            {
                path: 'add-client',
                title: 'Add Client',
                loadComponent: () => import('./dashboard/pages/add-client/add-client.component')
            },
        
            {
                path: 'service',
                title: 'Service',
                loadComponent: () => import('./dashboard/pages/product/service.component')
            },

            {
                path: 'add-service',
                title: 'Add Service',
                loadComponent: () => import('./dashboard/pages/add-service/add-service.component')
            },

            {
                path: 'quotation',
                title: 'Quotation',
                loadComponent: () => import('./dashboard/pages/quotation/quotation.component')
            },

            {
                path: 'add-quotation',
                title: 'Add Quotation',
                loadComponent: () => import('./dashboard/pages/add-quotation/add-quotation.component')
            },
            
            {
                path:'',
                redirectTo:'home',
                pathMatch: 'full'
            },
        
        ]
    },

    {
        path: 'register',
        loadComponent: () => import('./register/register.component')
    },

    {
        path: 'login',
        loadComponent: () => import('./login/login.component')
    },

    
    {
        path:'',
        redirectTo:'/login',
        pathMatch: 'full'
    }
];

function [X]=Fat_LU(L,U,B)
    
    [n,m]=size(L); 
    X(n,1)=0;
    
    for i=1:n-1
        
        for j=i+1:n   
              
            m=L(j,i);     
            B(j)=B(j)-B(i)*m;
            
        end
    end 
        
    for i=n:-1:1
        
        soma=0;
        
        for j=i+1:n  
                 
            soma=soma+U(i,j)*X(j,1);    
               
        end 
          
        X(i,1)=(B(i)-soma)/U(i,i);   
    end    
endfunction
